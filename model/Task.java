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
    /**
     * @param descrizione testo della task
     * @param priorita livello di priorità
     * @throws IllegalArgumentException se descrizione è vuota/null o priorità è null (Comandamento #4, #9)
     */
    public Task(String descrizione, Priorita priorita){
        this.id = UUID.randomUUID();
        setDescrizione(descrizione);
        setPriorita(priorita);
        setCompletata(false);
    }
    /**
     * @throws IllegalArgumentException se descrizione è null o vuota (Comandamento #4, #9)
     */
    public void setDescrizione(String descrizione) {
        // Fail-Fast (Comandamento #4): verifica immediata del parametro
        if (descrizione == null || descrizione.trim().isEmpty()) {
            throw new IllegalArgumentException("La descrizione non può essere null o vuota.");
        }
        this.descrizione = descrizione;
    }
    /**
     * @throws IllegalArgumentException se priorita è null (Comandamento #4, #9)
     */
    public void setPriorita(Priorita priorita) {
        // Fail-Fast (Comandamento #4): verifica immediata del parametro
        if (priorita == null) {
            throw new IllegalArgumentException("La priorità non può essere null.");
        }
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
        return "Task " + getId() + "\n Descrizione: " + getDescrizione() + "\n Priorita': " + getPriorita() + "\n IsCompletata: " + isCompletata();
    }
}
