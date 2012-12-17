/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodados.jdbc.thread;

import bancodados.IBanco;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author desenv01
 */
public class ThreadBanco implements IBanco {

    private static Queue<String> statements = new LinkedList<>();
    private Connection conn = null;
    private Worker worker;

    public ThreadBanco() {
        try {
            Logger.getLogger(getClass().getName()).info("Connecting to database...");
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost/teste", "root", "rootroot");
            worker = new Worker(this);
            Logger.getLogger(getClass().getName()).info("Starting worker thread...");
            worker.start();
        } catch (SQLException ex) {
            Logger.getLogger(ThreadBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized Connection getConnection() {
        return this.conn;
    }

    public synchronized ResultSet executeSelect(String command) {
        worker.addStatement(command);
        try {
            wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
        return worker.getRs();
    }

    public synchronized void executeUpdate(String command) {
        worker.addStatement(command);
    }

    public static class Worker extends Thread {

        private ThreadBanco banco;
        private ResultSet rs;

        public Worker(ThreadBanco banco) {
            this.banco = banco;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    try {
                        while (statements.isEmpty()) {
                            wait();
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ThreadBanco.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                String st = statements.poll();
                try {
                    PreparedStatement pst = banco.getConnection().prepareStatement(st);
                    Logger.getLogger(ThreadBanco.class.getName()).info("Executing query " + st);
                    if (st.trim().toUpperCase().startsWith("SELECT")) {
                        rs = pst.executeQuery();
                        synchronized (banco) {
                            banco.notify();
                        }
                    } else {
                        pst.executeUpdate();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ThreadBanco.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        public synchronized void addStatement(String st) {
            statements.add(st);
            notify();
        }

        public ResultSet getRs() {
            return rs;
        }
    }
}
