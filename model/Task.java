package model;
/*
Un semplice oggetto (POJO) con campi privati: id (int o UUID), descrizione (String), priorita (Enum), e completata (boolean).
Usa incapsulamento puro (getter e setter).
*/

import java.util.UUID;

public class Task {
    private final UUID id;
    private String descrizione;
    public enum Priorita{
        BASSA, MEDIA, ALTA
    }
    private Priorita priorita;
    private boolean completata;
    public Task(String descrizione, Priorita priorita){
        this.id = UUID.randomUUID();
        setDescrizione(descrizione);
        setPriorita(priorita);
        setCompletata(false);
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public void setPriorita(Priorita priorita) {
        this.priorita = priorita;
    }
    public void setCompletata(boolean completata) {
        this.completata = completata;
    }
    public String getDescrizione() {
        return descrizione;
    }
    public Priorita getPriorita() {
        return priorita;
    }
    public boolean isCompletata() {
        return completata;
    }
    public UUID getId() {
        return id;
    }
    @Override
    public String toString() {
        return "Task " + getId() + "\nDescrizione: " + getDescrizione() + "\nPriorita': " + getPriorita() + "\nIsCompletata: " + isCompletata();
    }
}
