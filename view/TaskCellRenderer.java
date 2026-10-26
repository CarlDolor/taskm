package view;

import model.Task;
import model.Task.Priorita;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TaskCellRenderer extends JPanel implements ListCellRenderer<Task> {
    private JLabel descrizioneLabel;
    private JLabel prioritaLabel;
    private JLabel statoLabel;

    public TaskCellRenderer() {
        setLayout(new BorderLayout(10, 2));
        setBorder(new EmptyBorder(8, 12, 8, 12));
        setOpaque(true);

        descrizioneLabel = new JLabel();
        descrizioneLabel.setFont(descrizioneLabel.getFont().deriveFont(Font.BOLD, 14f));

        prioritaLabel = new JLabel();
        prioritaLabel.setFont(prioritaLabel.getFont().deriveFont(Font.BOLD, 11f));

        statoLabel = new JLabel();
        statoLabel.setFont(statoLabel.getFont().deriveFont(Font.PLAIN, 11f));

        JPanel dettagliPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        dettagliPanel.setOpaque(false);
        dettagliPanel.add(prioritaLabel);
        dettagliPanel.add(statoLabel);

        add(descrizioneLabel, BorderLayout.CENTER);
        add(dettagliPanel, BorderLayout.SOUTH);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Task> list, Task task,
            int index, boolean isSelected, boolean cellHasFocus) {

        // --- Descrizione + ID ---
        String id = task.getId().toString().substring(0, 8);
        if (task.isCompletata()) {
            descrizioneLabel.setText("<html><s>" + task.getDescrizione() + "</s> <font color='gray'>[" + id + "]</font></html>");
        } else {
            descrizioneLabel.setText("<html>" + task.getDescrizione() + " <font color='gray'>[" + id + "]</font></html>");
        }

        // --- Priorità con colore ---
        Priorita p = task.getPriorita();
        prioritaLabel.setText("\u25CF " + p.name());
        switch (p) {
            case ALTA:  prioritaLabel.setForeground(new Color(210, 45, 45));  break;
            case MEDIA: prioritaLabel.setForeground(new Color(220, 155, 0));  break;
            case BASSA: prioritaLabel.setForeground(new Color(50, 155, 50));  break;
        }

        // --- Stato completamento ---
        if (task.isCompletata()) {
            statoLabel.setText("\u2714 Completata");
            statoLabel.setForeground(new Color(50, 155, 50));
        } else {
            statoLabel.setText("\u25CB Da fare");
            statoLabel.setForeground(Color.GRAY);
        }

        // --- Colori selezione / zebra striping ---
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            descrizioneLabel.setForeground(list.getSelectionForeground());
        } else {
            Color bg = (index % 2 == 0) ? list.getBackground() : new Color(243, 243, 243);
            setBackground(bg);
            descrizioneLabel.setForeground(task.isCompletata() ? Color.GRAY : list.getForeground());
        }

        return this;
    }
}
