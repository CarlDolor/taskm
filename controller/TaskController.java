package controller;
import view.TaskView;
import model.TaskModel;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import model.Task;
public class TaskController{
    private TaskModel tm;
    private TaskView tw;
    public TaskController(TaskModel tm, TaskView tw){
        this.tm = tm;
        this.tw = tw;
        this.tw.getAggiungiButton().addActionListener(e -> aggiungiNuovaTask());
        this.tw.getEliminaButton().addActionListener(e -> eliminaTaskSelezionata());
        this.tw.getCompletaButton().addActionListener(e -> completaTaskSelezionata());

        // Shortcut ENTER -> aggiungiNuovaTask
        tw.getAggiungiButton().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "aggiungi");
        tw.getAggiungiButton().getActionMap().put("aggiungi", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aggiungiNuovaTask();
            }
        });

        // Shortcut DELETE -> eliminaTaskSelezionata
        tw.getEliminaButton().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "elimina");
        tw.getEliminaButton().getActionMap().put("elimina", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminaTaskSelezionata();
            }
        });
    }
    private void aggiungiNuovaTask(){
        /*
        Legge il testo dalla View (view.getInserimentoDescrizione()).

        Controllo di sicurezza: Se il testo è vuoto, non fa nulla (puoi usare un semplice if).

        Legge la priorità dalla View (view.getSceltaPriorita()).

        Crea un nuovo oggetto Task(testo, priorita).

        Lo passa al Model (model.aggiungiTask(nuovaTask)).

        Pulisce il campo di testo (view.clearDescrizioneText()).

        Cruciale: Dice alla View di aggiornarsi passando la nuova lista (view.aggiornaLista(model.getTasks())). */
        String testo = tw.getInserimentoDescrizione();
        if (testo == null || testo.trim().isEmpty()) {
            return;
        }
        // Prevenzione (Comandamento #2): verifica preventiva della priorità
        // invece di catturare una possibile eccezione a runtime
        if (tw.getSceltaPriorita() == null) {
            return;
        }
        Task t = new Task(testo.trim(), tw.getSceltaPriorita());
        tm.aggiungiTask(t);
        tw.clearDescrizioneText();
        tw.aggiornaLista(tm.getTasks());
    }
    private void eliminaTaskSelezionata(){
        /* 
        Chiede alla View quale task è selezionata (view.getTaskSelected()).

        Controllo di sicurezza: Se è null (nessuna riga selezionata), interrompe l'esecuzione.

        Rimuove la task dal Model.

        Aggiorna la View.
        */
        // Prevenzione (Comandamento #2): controllo preventivo su task selezionata
        Task selected = tw.getTaskSelected();
        if (selected == null) {
            return;
        }
        tm.rimuoviTask(selected);
        tw.aggiornaLista(tm.getTasks());
    }
    private void completaTaskSelezionata(){
        /*
                Prende la task selezionata.

        Se non è null, imposta il suo stato su completata (task.setCompletata(true)).

        Aggiorna la View (così il toString() aggiornato con la "X" verrà disegnato a schermo).
         */
        // Prevenzione (Comandamento #2): controllo preventivo su task selezionata invece di catch (Exception e)
        Task selected = tw.getTaskSelected();
        if (selected == null) {
            return;
        }
        selected.setCompletata(true);
        tw.aggiornaLista(tm.getTasks());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 1. Crei il Model
            TaskModel model = new TaskModel();
            // 2. Crei la View
            TaskView view = new TaskView();
            // 3. Crei il Controller e li colleghi
            TaskController controller = new TaskController(model, view);
        });
    }
}