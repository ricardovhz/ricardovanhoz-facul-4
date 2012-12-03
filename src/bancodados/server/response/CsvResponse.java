/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodados.server.response;

import java.util.Iterator;

/**
 *
 * @author desenv01
 */
public class CsvResponse extends AbstractResponse {

    private String columns;
    private String[] data;

    public CsvResponse(String status) {
        super(status);
    }

    public CsvResponse(String status, String response) {
        super(status, response);
    }

    @Override
    public void process(String response) {
        String[] lines = response.split("\n");
        columns = lines[0];
        data = new String[lines.length - 1];
        for (int i = 1; i < lines.length; i++) {
            data[i - 1] = lines[i];
        }
    }
    
    public String getColumnNames() {
        return columns;
    }

    @Override
    public Iterator<Line> iterator() {
        if (data != null) {
            return new CsvResponseIterator(data);
        } else {
            throw new RuntimeException("data==null");
        }
    }
    
    public class CsvResponseIterator implements Iterator<Line> {

        private String[] lines;
        private int index = 0;

        public CsvResponseIterator(String[] lines) {
            this.lines = lines;
        }

        @Override
        public boolean hasNext() {
            return index < lines.length;
        }

        @Override
        public CsvLine next() {
            return new CsvLine(lines[index++],columns.split(","));
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
