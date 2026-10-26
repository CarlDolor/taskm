package view;

import model.Task;
import model.Task.Priorita;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.lang.classfile.constantpool.ClassEntry;
import java.util.List;
/*
1. Elementi Grafici (Campi della classe)

Dichiara questi componenti Swing come variabili private all'interno della classe:

    Input: Un JTextField per scrivere la descrizione della task.

    Selezione: Un JComboBox<Priorita> per scegliere la priorità (puoi popolarlo passandogli Priorita.values()).

    Visualizzazione: Una JList<Task> per mostrare l'elenco. Per poter aggiornare dinamicamente questa lista a runtime, dovrai istanziarla usando un modello di default: new JList<>(new DefaultListModel<Task>()).

    Bottoni: Tre JButton ("Aggiungi", "Completa", "Elimina").

2. Struttura del Layout (Nel costruttore)

Costruisci la finestra (JFrame) utilizzando un BorderLayout per dividere le aree:

    Area NORD: Crea un JPanel (magari con FlowLayout) e inseriscici il campo di testo, la tendina delle priorità e il tasto "Aggiungi".

    Area CENTRO: Inserisci la JList all'interno di un JScrollPane (per avere la barra di scorrimento quando ci sono tante task), e posiziona quest'ultimo al centro.

    Area SUD: Crea un altro JPanel e inseriscici i pulsanti "Completa" e "Elimina".

3. I Metodi Pubblici (L'interfaccia verso l'esterno)

Il Controller avrà bisogno di accedere a questi elementi senza violare l'incapsulamento. Esponi questi metodi:

    Getter per i bottoni: public JButton getBtnAggiungi(), getBtnCompleta(), getBtnElimina(). Serviranno al Controller per fare l' addActionListener.

    Lettura input: Un metodo public String getInputDescrizione() che restituisce il testo digitato nel JTextField (e magari un metodo per pulire il campo dopo aver aggiunto la task).

    Lettura selezione: Un metodo public Priorita getPrioritaSelezionata() che legge il valore dal JComboBox.

    Lettura lista: Un metodo public Task getTaskSelezionata() che usa lista.getSelectedValue() per capire quale riga l'utente vuole eliminare o completare.

    Aggiornamento vista: Un metodo public void aggiornaLista(List<Task> tasks) che pulisce il DefaultListModel della JList e reinserisce tutte le task aggiornate.

*/
public class TaskView{
    private JTextField inserimentoDescrizioneTextField;
    private JComboBox<Priorita> sceltaPrioritaBox = new JComboBox<>(Task.Priorita.values());
    private JList<Task> elencoTask = new JList<>(new DefaultListModel<Task>());
    {
        elencoTask.setCellRenderer(new TaskCellRenderer());
        elencoTask.setFixedCellHeight(52);
    }
    private JButton aggiungiButton, completaButton, eliminaButton;
    private JScrollPane taskJscrollPane = new JScrollPane(elencoTask);


    public TaskView(){
        aggiungiButton = new JButton("Aggiungi");
        completaButton = new JButton("Completa");
        eliminaButton = new JButton("Elimina");
        inserimentoDescrizioneTextField = new JTextField(50);

        JPanel masterFrame = new JPanel();
        masterFrame.setLayout(new BorderLayout());
        //Nord
        JPanel nordJPanel = new JPanel();
        nordJPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(25, 25, 5, 25);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        nordJPanel.add(inserimentoDescrizioneTextField, gbc);
        gbc.insets = new Insets(0, 25, 25, 5);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.3;
        nordJPanel.add(sceltaPrioritaBox, gbc);
        gbc.insets = new Insets(0, 5, 25, 25);
        gbc.gridx=1;
        gbc.weightx = 0.7;
        nordJPanel.add(aggiungiButton, gbc);

        //Centro
        JPanel centraPanel = new JPanel();
        centraPanel.setLayout(new GridBagLayout());
        gbc.insets = new Insets(20, 0, 20, 0);
        gbc.gridx = 0;
        gbc.gridy=0;
        gbc.weightx = 0.2;
        gbc.weighty=0.5;
        gbc.fill = GridBagConstraints.BOTH;
        centraPanel.add(Box.createGlue(), gbc);
        gbc.gridy=0;
        gbc.gridx = 1;
        gbc.weightx = 0.6;
        gbc.fill = GridBagConstraints.BOTH;
        centraPanel.add(taskJscrollPane, gbc);
        gbc.gridx = 2;
        gbc.gridy=0;
        gbc.weightx = 0.2;
        gbc.fill = GridBagConstraints.BOTH;
        centraPanel.add(Box.createGlue(), gbc);

        //South
        JPanel southJPanel = new JPanel();
        southJPanel.setLayout(new GridBagLayout());
        gbc.insets = new Insets(25, 50, 25, 25);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        southJPanel.add(completaButton, gbc);
        gbc.insets = new Insets(25, 50, 25, 25);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        southJPanel.add(eliminaButton, gbc);


        masterFrame.add(nordJPanel, BorderLayout.NORTH);
        masterFrame.add(centraPanel, BorderLayout.CENTER);
        masterFrame.add(southJPanel, BorderLayout.SOUTH);

        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(700,700));
        frame.setSize(500, 500);
        frame.add(masterFrame, BorderLayout.CENTER);
        frame.setResizable(true);
        frame.setVisible(true);
    }

    public JButton getAggiungiButton() {
        return aggiungiButton;
    }
    public JButton getCompletaButton() {
        return completaButton;
    }
    public JButton getEliminaButton() {
        return eliminaButton;
    }

    public String getInserimentoDescrizione() {
        return inserimentoDescrizioneTextField.getText();
    }
    public void clearDescrizioneText(){
        inserimentoDescrizioneTextField.setText("");
    }

    public Priorita getSceltaPriorita() {
        return (Priorita) sceltaPrioritaBox.getSelectedItem();
    }

    public Task getTaskSelected(){
        return elencoTask.getSelectedValue();
    }

    public void aggiornaLista(List<Task> tasks){
        DefaultListModel<Task> model = (DefaultListModel<Task>) elencoTask.getModel();
        model.clear();
        for (Task task : tasks) {
            model.addElement(task);
        }
    }
}