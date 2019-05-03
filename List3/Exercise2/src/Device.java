import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Device extends Thread {


    private Cable cab;

    Device(Cable c) {
        this.cab = c;
    }

    @Override
    public void run() {
        try {
            while (!cab.canTransmit()) {
                Random r = new Random();
                Thread.sleep(r.nextInt(5) * 1000);
            }

            cab.add("Device: " + this.getId() + " START");
            Thread.sleep(500);
            String message = "Data From: " + this.getId() +" Device";
            cab.add(message);
            Thread.sleep(500);
            cab.add("Device: " + this.getId() + " END");

            String l1, l3;
            l3 = cab.data.get(cab.data.size() - 1);
            l1 = cab.data.get(cab.data.size() - 3);

            if (l1.equals("Device: " + this.getId() + " START")
                    &&
                    l3.equals("Device: " + this.getId() + " END")) {
                System.out.println("Device " + this.getId() + " has successfully transmitted data.");
                cab.succ++;
            } else {
                cab.add("JAM\t " + this.getId() + "  Collision!");
            }
        } catch (IOException | InterruptedException | NullPointerException ex) {
            Logger.getLogger(Device.class.getName()).log(Level.SEVERE, null, ex);

        }



        // jeśli się zmienia, to czekam od 1 do 5 sekund

        // jeśli mogę zapisywać, to piszę:
        // [BEGIN nr. wątku]
        // czekam 0,5 s
        // wpisuje losową liczbę (0 - 100000) znak po znaku w taki sposób, aby kolejne litery pojawiały się co 0.5 s
        // wpisuję [END nr. wątku]

        // wczytuję 3 ostatnie linijki pliku
        // jeśli pierwsza i ostatnia są równe moim nagłówkom [BEGIN] i [END] to zatwierdzam transmisję jako udaną
        // jeśli nie, to dopisuję do pliku [JAM nr. wątku]
    }


}