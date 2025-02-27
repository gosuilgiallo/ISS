package conway;

/**
 * Rappresenta una singola cella nel Game of Life.
 * Gestisce lo stato della cella (viva o morta).
 */
public class Cell {
    private static final int ALIVE = 1;
    private static final int DEAD = 0;
    
    private int state;
    
    /**
     * Costruttore che crea una cella morta di default
     */
    public Cell() {
        this.state = DEAD;
    }
    
    /**
     * Costruttore che permette di specificare lo stato iniziale
     * @param alive true se la cella deve essere viva, false altrimenti
     */
    public Cell(boolean alive) {
        this.state = alive ? ALIVE : DEAD;
    }
    
    /**
     * @return true se la cella è viva, false altrimenti
     */
    public boolean isAlive() {
        return state == ALIVE;
    }
    
    /**
     * @return lo stato della cella come intero (1=viva, 0=morta)
     */
    public int getState() {
        return state;
    }
    
    /**
     * Imposta lo stato della cella
     * @param alive true per renderla viva, false per renderla morta
     */
    public void setState(boolean alive) {
        this.state = alive ? ALIVE : DEAD;
    }
    
    /**
     * Cambia lo stato della cella (se viva diventa morta, se morta diventa viva)
     */
    public void switchState() {
        this.state = isAlive() ? DEAD : ALIVE;
    }
    
    /**
     * Applica le regole del Game of Life per determinare il prossimo stato della cella
     * @param numNeighbors numero di vicini vivi
     * @return il nuovo stato della cella
     */
    public int computeNextState(int numNeighbors) {
        if (isAlive()) {
            // Cella viva
            if (numNeighbors < 2) {
                // Muore per isolamento
                return DEAD;
            } else if (numNeighbors == 2 || numNeighbors == 3) {
                // Sopravvive
                return ALIVE;
            } else {
                // Muore per sovrappopolazione
                return DEAD;
            }
        } else {
            // Cella morta
            if (numNeighbors == 3) {
                // Rinasce per riproduzione
                return ALIVE;
            }
            // Altrimenti rimane morta
            return DEAD;
        }
    }
    
    @Override
    public String toString() {
        return String.valueOf(state);
    }
}