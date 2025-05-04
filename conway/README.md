# Link relazione finale della prima fase (conway life game)

[Relazione finale fase 1](./ISS_Conway.pdf)


# Conway Life Game #
The Game of Life by John Conway is a zero-player cellular automaton that simulates how cells live, die, or reproduce based on simple rules.  
It was invented in 1970 and is often used in computer science, mathematics, and artificial intelligence studies.
## 🎲 How it Works ##

The game consists of a grid of cells, where each cell can be either:  

    Alive (🟩) or Dead (⬜️)
    The game progresses in generations, following these four rules:

1️⃣ **Underpopulation**: A live cell with fewer than 2 live neighbors dies.  
2️⃣ **Overpopulation**: A live cell with more than 3 live neighbors dies.  
3️⃣ **Survival**: A live cell with 2 or 3 neighbors stays alive.  
4️⃣ **Reproduction**: A dead cell with exactly 3 live neighbors becomes alive.  
🖥 Example of an Initial Grid (5x5)  

⬜️ ⬜️ 🟩 ⬜️ ⬜️  
🟩 ⬜️ 🟩 ⬜️ ⬜️  
⬜️ 🟩 🟩 ⬜️ ⬜️  
⬜️ ⬜️ ⬜️ ⬜️ ⬜️  
⬜️ ⬜️ ⬜️ ⬜️ ⬜️  

This pattern might evolve into something new in the next generation!  
_⚡ Why is it Interesting?_

- It demonstrates emergent behavior from simple rules.  
- Some patterns oscillate, some move, and some even replicate themselves!  
- It has been used in AI, complex systems, and even music generation.  


## Content of this project ##
- **conway25Java**: basic logic
- **conwaygui**: incremental development of a Graphic User Interface
- **conway25JavaMqtt** : communication with mqtt
- **java-microservice-app**: an example of microservice
- **conwayguialone**: a stand-alone GUI
