------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all they need to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE:
Bad DOPO Cream

PURPOSE OF PROJECT:
Bad DOPO Cream es una recreación académica de los primeros niveles del juego Bad Ice-Cream,
desarrollada para el curso Desarrollo Orientado por Objetos (DOPO 2025-2). El objetivo es
aplicar principios de diseño orientado a objetos para construir un videojuego 2D con
mecánicas de movimiento, laberintos de hielo, creación/ruptura de bloques y recolección
de frutas mientras se evitan enemigos con distintos comportamientos. 

VERSION or DATE:
Versión 1.0 – Semestre 2025-2

HOW TO START THIS PROJECT:
1. Abrir el proyecto en BlueJ/NetBeans.
2. Localizar la clase VentanaJuego en el paquete Presentacion.
3. Ejecutar el método main de VentanaJuego para iniciar la aplicación.
4. Desde el menú inicial seleccionar modalidad, sabor de helado y nivel para empezar a jugar.

AUTHORS:
Santiago Andres Gomez Rojas
Miguel Angel Sandoval
(Proyecto Bad DOPO Cream – DOPO 2025-2)
USER INSTRUCTIONS:
- Objetivo general:
  Recolectar todas las frutas de cada nivel (8 uvas y 8 plátanos en el nivel 1) antes de que
  se acabe el tiempo o un enemigo toque al helado. 

- Controles:
  Flechas o WASD para mover el helado en cuatro direcciones.
  Barra espaciadora para crear o romper bloques de hielo en la dirección a la que mira el helado.
  Tecla P o ESC para pausar/reanudar la partida.

- Mecánicas principales:
  El helado no puede atravesar bloques de hielo ni el iglú.
  Puede crear líneas de bloques para bloquear caminos o protegerse, y romper líneas de bloques
  (tanto del mapa como creados por el jugador) en efecto dominó.
  Los enemigos (trolls en el nivel 1) siguen patrones de movimiento predeterminados y
  eliminan al helado al tocarlo. 

- Condiciones de victoria y derrota:
  Se gana el nivel al recolectar todas las frutas requeridas.
  Se pierde si un enemigo toca al helado o si se agota el tiempo del nivel (3 minutos).
  Al perder se ofrece reiniciar el nivel; al ganar se muestra un mensaje de victoria con
  puntaje basado en frutas recolectadas y tiempo restante. 
