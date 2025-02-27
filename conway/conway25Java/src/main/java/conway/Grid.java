package conway;

/**
 * Gestisce la griglia di celle nel Game of Life.
 * Responsabile della struttura dati della griglia e delle operazioni su di essa.
 */
public class Grid {
    private int rows;
    private int cols;
    private Cell[][] grid;
    private Cell[][] nextGrid;
    
    /**
     * Costruttore della griglia con dimensioni specificate
     * @param rows numero di righe
     * @param cols numero di colonne
     */
    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        createGrids();
    }
    
    /**
     * @return il numero di righe della griglia
     */
    public int getRows() {
        return rows;
    }
    
    /**
     * @return il numero di colonne della griglia
     */
    public int getCols() {
        return cols;
    }
    
    /**
     * Crea le strutture dati per la griglia corrente e la prossima generazione
     */
    private void createGrids() {
        grid = new Cell[rows][cols];
        nextGrid = new Cell[rows][cols];
        
        // Inizializza tutte le celle come oggetti
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell();
                nextGrid[i][j] = new Cell();
            }
        }
    }
    
    /**
     * Resetta entrambe le griglie a celle morte
     */
    public void resetGrids() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j].setState(false);
                nextGrid[i][j].setState(false);
            }
        }
    }
    
    /**
     * Accede a una cella specifica
     * @param row riga
     * @param col colonna
     * @return la cella alla posizione specificata
     */
    public Cell getCell(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            return grid[row][col];
        }
        return null; // Fuori dai limiti
    }
    
    /**
     * Modifica lo stato di una cella
     * @param row riga
     * @param col colonna
     */
    public void switchCellState(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            grid[row][col].switchState();
        }
    }
    
    /**
     * Restituisce lo stato di una cella come intero
     * @param row riga
     * @param col colonna
     * @return 1 se la cella è viva, 0 se è morta
     */
    public int getCellState(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            return grid[row][col].getState();
        }
        return 0; // Fuori dai limiti, considera come morta
    }
    
    /**
     * Conta il numero di celle vicine vive
     * @param row riga
     * @param col colonna
     * @return numero di vicini vivi
     */
    public int countNeighborsLive(int row, int col) {
        int count = 0;
        
        // Le 8 celle adiacenti (controllo dei bordi incluso)
        int[][] directions = {
            {-1, 0}, {-1, -1}, {-1, 1},  // sopra, sopra-sinistra, sopra-destra
            {0, -1}, {0, 1},              // sinistra, destra
            {1, 0}, {1, -1}, {1, 1}       // sotto, sotto-sinistra, sotto-destra
        };
        
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                if (grid[newRow][newCol].isAlive()) {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    /**
     * Calcola la prossima generazione della griglia
     * @param outdev dispositivo di output per visualizzare i cambiamenti
     */
    public void computeNextGeneration(IOutDev outdev) {
        // Calcola il prossimo stato per ogni cella
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int numNeighbors = countNeighborsLive(i, j);
                int nextState = grid[i][j].computeNextState(numNeighbors);
                nextGrid[i][j].setState(nextState == 1);
                
                if (outdev != null) {
                    outdev.displayCell(grid[i][j].toString());
                }
            }
            if (outdev != null) {
                outdev.displayCell("\n");
            }
        }
        
        // Aggiorna la griglia con la nuova generazione
        updateGrid(outdev);
        
        if (outdev != null) {
            outdev.displayCell("\n");
        }
    }
    
    /**
     * Copia la griglia della prossima generazione nella griglia corrente
     * @param outdev dispositivo di output
     */
    private void updateGrid(IOutDev outdev) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j].setState(nextGrid[i][j].isAlive());
                nextGrid[i][j].setState(false);
            }
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Se è lo stesso oggetto, sono uguali
        if (obj == null || getClass() != obj.getClass()) return false; // Controllo tipo e null

        Grid other = (Grid) obj;

        // Controllo dimensioni
        if (this.getRows() != other.getRows() || this.getCols() != other.getCols()) {
            return false;
        }

        // Controllo stato delle celle
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                if (this.getCell(i, j).getState() != other.getCell(i, j).getState()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                sb.append(getCell(i, j).getState() == 1 ? "1" : "0");
            }
            sb.append("\n"); // Vai a capo dopo ogni riga
        }
        
        return sb.toString();
    }


}
