package model;
import java.util.ArrayList;
import java.util.List;
/*
Le specifiche per la classe TaskModel

Crea una nuova classe TaskModel (sempre nel package model) e prova a implementare questi elementi:

    La Struttura Dati: Un campo privato di tipo List<Task>, che verrà inizializzato nel costruttore della classe come un nuovo ArrayList<Task>().

    Aggiunta: Un metodo public void aggiungiTask(Task t) che prende un oggetto Task e lo inserisce nella lista.

    Rimozione: Un metodo public void rimuoviTask(Task t) che elimina una specifica task dalla lista.

    Lettura: Un metodo public List<Task> getTasks() che restituisce l'intera collezione, in modo che la futura interfaccia grafica possa leggerla per stamparla a schermo.

*/
public class TaskModel{
    private List<Task> tasks;
    public TaskModel(List<Task> tasks){
        this.tasks = new ArrayList<>();
    }
    public void aggiungiTask(Task t){
        tasks.add(t);
    }
    public void rimuoviTask(Task t){
        tasks.remove(t);
    }
    public List<Task> getTasks(){
        return List.copyOf(this.tasks);
    }
}