# HES programm 

(pildid siia ...)
Eesmärk ....

## Tiimiliikmed
* Kurmo Andres Rootsi
* Markus Heinsalu
* Erik Enden
* Daisy Pukkonen

## Programmi link 
--------------

## HES arenduskeskkonna seadistamine 
1. Vajalik tarkvara 
* Java SE Development Kit 8 Update 151 (64-bit) (versioon 8.0.1510.12)
* Google Cloud SDK ja Appengine Java component
* Eclipse Oxygen.2 Release (4.7.2)
* Google Cloud Tools for Eclipse 1.6.0 
* GWT Eclipse Plugin 3.0.0

2. HES projekti importimine ja käivitamine
* Lae alla aho.zip fail siit -->
* Paki lahti .zip fail ning lisa githubist võetud /src ning /war aho kausta
* Lisa aho kaust oma Eclipse'i töökataloogi (vaikimisi C:\Users\{nimi}\eclipse/workspace)
* Käivita Eclipse ning impordi HES projekt Eclipse'i
  File > Open Projects from File System...
  Import source > otsi projekti kataloogi (vaikimisi C:\Users\{nimi}\eclipse-workspace\elektrimasinad\aho(
* Tee parem klikk projektil ning vali
  Configure > Convert to Appengine Standard
* Seejärel 
  GWT > Compile
* Projekti käivitamiseks vali
  Run as > App Engine 
3. Lisa
* Kindlasti vaata, et oleks õige versioon JDK library peal
* Muudatuste tegemisel peab igakord GWT(compile) tegema ning alles siis Run as(App Engine)




