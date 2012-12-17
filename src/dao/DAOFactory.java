/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bancodados.IBanco;
import bancodados.jdbc.BancoDados;
import bancodados.jdbc.thread.ThreadBanco;
import bancodados.server.IServer;
import dao.jdbc.JDBCLogradouroDAO;
import dao.jdbc.JDBCMultaDAO;
import dao.jdbc.JDBCProprietarioDAO;
import dao.jdbc.JDBCVeiculoDAO;
import dao.jdbc.thread.ThreadLogradouroDAO;
import dao.jdbc.thread.ThreadMultaDAO;
import dao.jdbc.thread.ThreadProprietarioDAO;
import dao.jdbc.thread.ThreadVeiculoDAO;
import dao.server.ServerLogradouroDAO;
import dao.server.ServerMultaDAO;
import dao.server.ServerProprietarioDAO;
import dao.server.ServerVeiculoDAO;
import tablemodel.AbstractTableModel;

/**
 *
 * @author desenv01
 */
public class DAOFactory {

    public static enum TIPO_BANCO {

        JDBC,
        SERVER,
        THREAD
    }

    private static TIPO_BANCO getTipo(IBanco banco) {
        if (banco instanceof BancoDados) {
            return TIPO_BANCO.JDBC;
        } else if (banco instanceof IServer) {
            return TIPO_BANCO.SERVER;
        } else if (banco instanceof ThreadBanco) {
            return TIPO_BANCO.THREAD;
        } else {
            return null;
        }
    }

    public static ProprietarioDAO getProprietarioDAO(IBanco banco) {
        switch (getTipo(banco)) {
            case JDBC:
                return new JDBCProprietarioDAO((BancoDados) banco);
            case SERVER:
                return new ServerProprietarioDAO((IServer) banco);
            case THREAD:
                return new ThreadProprietarioDAO((ThreadBanco) banco);
        }
        return null;
    }

    public static VeiculoDAO getVeiculoDAO(IBanco banco) {
        switch (getTipo(banco)) {
            case JDBC:
                return new JDBCVeiculoDAO((BancoDados) banco);
            case SERVER:
                return new ServerVeiculoDAO((IServer) banco);
            case THREAD:
                return new ThreadVeiculoDAO((ThreadBanco) banco);
        }
        return null;
    }

    public static LogradouroDAO getLogradouroDAO(IBanco banco) {
        switch (getTipo(banco)) {
            case JDBC:
                return new JDBCLogradouroDAO((BancoDados) banco);
            case SERVER:
                return new ServerLogradouroDAO((IServer) banco);
            case THREAD:
                return new ThreadLogradouroDAO((ThreadBanco) banco);
        }
        return null;
    }

    public static MultaDAO getMultaDAO(IBanco banco) {
        switch (getTipo(banco)) {
            case JDBC:
                return new JDBCMultaDAO((BancoDados) banco);
            case SERVER:
                return new ServerMultaDAO((IServer) banco);
            case THREAD:
                return new ThreadMultaDAO((ThreadBanco) banco);
        }
        return null;
    }
}
