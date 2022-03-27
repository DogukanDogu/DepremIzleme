import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SonDepremlerGUI {
    private JFrame frame;
    private List<HashMap<String, Object>> listDepremData = new LinkedList<HashMap<String, Object>>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SonDepremlerGUI window = new SonDepremlerGUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public SonDepremlerGUI() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 839, 585);
        frame.setSize(new Dimension(1069, 735));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(0,0));

        JPanel panelDepremTable = new JPanel();
        panelDepremTable.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPaneDepremTable = new JScrollPane(panelDepremTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPaneDepremTable.setViewportView(panelDepremTable);
        DepremTableModel tableModelDeprem = new DepremTableModel();

        JTextPane textTitle = new JTextPane();
        textTitle.setFont(new Font("Arial", Font.BOLD, 14));
        textTitle.setEditable(false);
        textTitle.setContentType("text/html");

        JTable tableDeprem = new JTable(tableModelDeprem);
        tableDeprem.setFillsViewportHeight(true);
        panelDepremTable.add(tableDeprem);
        panelDepremTable.add(tableDeprem.getTableHeader(), BorderLayout.NORTH);

        frame.add(scrollPaneDepremTable);

        ParseHTML html = new ParseHTML();
        html.setAddress("http://www.koeri.boun.edu.tr/scripts/lst5.asp");
        html.connectWEBSite();

        StringBuffer sbHeader = new StringBuffer();
        StringBuffer sbColumnHeaders = new StringBuffer();

        String[] lines = html.getText().split(System.getProperty("line.separator"));

        sbHeader.append(lines[0] + "\n");
        sbHeader.append(lines[1] + "\n");
        sbHeader.append(lines[2] + "\n");

        sbColumnHeaders.append(lines[4]);

        String[] titles = sbHeader.toString().toUpperCase().split("\\.");
        StringBuffer sbTitle = new StringBuffer();

        for (int i = 0; i < titles.length; i++) {
            sbTitle.append(titles[i] + " ");
        }

        textTitle.setText("<html><center><b> " + sbTitle.toString() + "  </b></center></html>");


        for (int i = 6; i < lines.length; i++) {
            String[] words = lines[i].split("[ ]+\\s*(\\s|=>|,)\\s*");
            HashMap<String, Object> map = new HashMap<String, Object>();

            map.put("TARIH", words[0]);
            map.put("ENLEM", words[1]);
            map.put("BOYLAM", words[2]);
            map.put("DERINLIK", words[3]);
            map.put("MD", words[4]);
            map.put("ML", words[5]);
            map.put("MW", words[6]);
            map.put("YER", words[7]);
            map.put("COZUMNITELIGI", words[8]);

            listDepremData.add(map);
        }

        tableModelDeprem.setList(listDepremData);

    }


    public class DepremTableModel extends AbstractTableModel {


        private String[] columnNames = { "Tarih - Saat", "Enlem (N)", "Boylam (E)", "Derinlik(km)", "MD", "ML", "Mw",
                "Yer", "Çözüm Niteliği" };
        private List<HashMap<String, Object>> listData = new LinkedList<HashMap<String, Object>>();

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public int getRowCount() {
            return listData == null ? 0 : listData.size();
        }

        @Override
        public String getColumnName(int index) {
            return columnNames[index];
        }

        @Override
        public Object getValueAt(int row, int col) {
            HashMap<String, Object> map = listData.get(row);

            switch (col) {
                case 0: {
                    if (map.get("TARIH") != null)
                        return map.get("TARIH");
                    else
                        return "";
                }
                case 1: {
                    if (map.get("ENLEM") != null)
                        return map.get("ENLEM");
                    else
                        return "";
                }
                case 2: {
                    if (map.get("BOYLAM") != null)
                        return map.get("BOYLAM");
                    else
                        return "";
                }
                case 3: {
                    if (map.get("DERINLIK") != null)
                        return map.get("DERINLIK");
                    else
                        return "";
                }
                case 4: {
                    if (map.get("MD") != null)
                        return map.get("MD");
                    else
                        return "";
                }
                case 5: {
                    if (map.get("ML") != null)
                        return map.get("ML");
                    else
                        return "";
                }
                case 6: {
                    if (map.get("MW") != null)
                        return map.get("MW");
                    else
                        return "";
                }
                case 7: {
                    if (map.get("YER") != null)
                        return map.get("YER");
                    else
                        return "";
                }
                case 8: {
                    if (map.get("COZUMNITELIGI") != null)
                        return map.get("COZUMNITELIGI");
                    else
                        return "";
                }
                default:
                    return null;
            }

        }

        public void setList(List<HashMap<String, Object>> list) {
            this.listData = list;
            fireTableDataChanged();
        }

    }
}
