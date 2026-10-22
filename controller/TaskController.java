package controller;
import view.TaskView;
import model.TaskModel;

import javax.swing.SwingUtilities;

import model.Task;

/*

*/

public class TaskController{
    private TaskModel tm;
    private TaskView tw;
    public TaskController(TaskModel tm, TaskView tw){
        this.tm = tm;
        this.tw = tw;
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
        if (tw.getInserimentoDescrizione().isEmpty()) {
            return;
        }
        Task t = new Task(tw.getInserimentoDescrizione(), tw.getSceltaPriorita());
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
       if (tw.getTaskSelected() == null) {
            return;
       }
       tm.rimuoviTask(tw.getTaskSelected());
       tw.aggiornaLista(tm.getTasks());
    }
    private void completaTaskSelezionata(){
        /*
                Prende la task selezionata.

        Se non è null, imposta il suo stato su completata (task.setCompletata(true)).

        Aggiorna la View (così il toString() aggiornato con la "X" verrà disegnato a schermo).
         */
        if (tw.getTaskSelected() == null) {
            return ;
        }
        tw.getTaskSelected().setCompletata(true);
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