package conway;

/**
 * Il core di game of life
 * Non ha dipendenze da dispositivi di input/output
 * Non ha regole di controllo del gioco
 */
public class Life {
    private Grid grid;
    
    /**
     * Costruttore che inizializza il gioco con una griglia di dimensioni specificate
     * @param rowsNum numero di righe
     * @param colsNum numero di colonne
     */
    public Life(int rowsNum, int colsNum) {
        this.grid = new Grid(rowsNum, colsNum);
    }
    
    /**
     * @return il numero di righe della griglia
     */
    public int getRowsNum() {
        return grid.getRows();
    }
    
    /**
     * @return il numero di colonne della griglia
     */
    public int getColsNum() {
        return grid.getCols();
    }
    
    /**
     * Crea le strutture dati per la griglia
     */
    protected void createGrids() {
        // La creazione è già delegata al costruttore di Griglia, 
        // ma manteniamo il metodo per compatibilità
    }
    
    /**
     * Resetta la griglia
     */
    protected void resetGrids() {
    	grid.resetGrids();
    }
    
    /**
     * Conta i vicini vivi di una cella
     * @param row riga
     * @param col colonna
     * @return numero di vicini vivi
     */
    protected int countNeighborsLive(int row, int col) {
        return grid.countNeighborsLive(row, col);
    }
    
    /**
     * Calcola la prossima generazione della griglia
     * @param outdev dispositivo di output
     */
    protected void computeNextGen(IOutDev outdev) {
    	grid.computeNextGeneration(outdev);
    }
    
    /**
     * Inverte lo stato di una cella
     * @param i riga
     * @param j colonna
     */
    public void switchCellState(int i, int j) {
    	grid.switchCellState(i, j);
    }
    
    /**
     * Ottiene lo stato di una cella
     * @param i riga
     * @param j colonna
     * @return stato della cella (1 = viva, 0 = morta)
     */
    public int getCellState(int i, int j) {
        return grid.getCellState(i, j);
    }
    
    /**
     * Accede direttamente alla griglia
     * @return l'oggetto griglia
     */
    public Grid getGrid() {
        return grid;
    }
}