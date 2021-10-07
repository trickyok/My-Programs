import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


/* BUGS:

Does not convert back to mL if it goes below L
usually when the bottle becomes empty

maybe convert percentage into a double instead for more
accurate measurements
 */




/* Gage Farmer
10/5/21
Fill up and empty a water bottle lol
 */
class waterBottle {

    // Declarations
    int percentFilled, totalPrefixIndex, waterPrefixIndex;
    double height, radius, totalVolume, waterVolume, totalVolumeConverted, waterVolumeConverted;
    String totalVolPrefix = "ml", waterVolPrefix = "ml";


    // Returning values
    public double getHeight() { return this.height; }
    public double getRadius() { return this.radius; }
    public double getTotalVolume() { return this.totalVolumeConverted; }
    public double getWaterVolume() { return this.waterVolumeConverted; }
    public int getPercentFilled() { return this.percentFilled; }


    // Setting values
    public void setHeight(double height) { this.height = height; }
    public void setRadius(double radius) { this.radius = radius; }
    public void setPercentFilled(int percent) {
        if (percent <= 100 && percent >= 0) { this.percentFilled = percent; }
        else { System.out.println("Invalid percentage. Value was not set."); }
    }


    // Manipulating values
    public void drinkWater() {
        int drinkSize = (int) (Math.random() * 10);
        if (this.percentFilled == 0) { return;
        } else if (this.percentFilled >= drinkSize) { this.percentFilled -= drinkSize; }
        else { this.percentFilled = 0; }
        calcWaterVolume();
    }
    public void fillBottle() {
        this.percentFilled = 100;
        calcWaterVolume();
    }


    // Calculating volumes based on dimensions and percent filled
    public void calcVolumes() { calcTotalVolume(); calcWaterVolume(); }
    private void calcTotalVolume() {
        this.totalVolume = Math.PI * (this.radius * this.radius) * this.height;
        this.totalVolumeConverted = this.totalVolume;
        convertTotalVolume();
    }
    private void calcWaterVolume() {
        this.waterVolume = this.totalVolume * (this.percentFilled / 100.0);
        this.waterVolumeConverted = this.waterVolume;
        this.waterPrefixIndex = 0;
        convertWaterVolume();
    }


    // Converting numbers to proper units
    public void convertTotalVolume() {
        do {
            if (this.totalVolumeConverted >= 1000) {
                this.totalVolumeConverted /= 1000;
                this.totalPrefixIndex += 1;
            }
            else if (this.totalVolumeConverted < 0) {
                this.totalVolumeConverted *= 1000;
                this.totalPrefixIndex -= 1;
            } else {
                break;
            }
            this.totalVolPrefix = setPrefix(totalPrefixIndex);
        } while (true);
    }
    public void convertWaterVolume() {
        do {
            if (this.waterVolumeConverted >= 1000) {
                this.waterVolumeConverted /= 1000;
                this.waterPrefixIndex += 1;
                this.waterVolPrefix = setPrefix(waterPrefixIndex);
            }
            else if (this.waterVolumeConverted < 0) {
                this.waterVolumeConverted *= 1000;
                this.waterPrefixIndex -= 1;
                this.waterVolPrefix = setPrefix(waterPrefixIndex);
            } else {
                break;
            }
        } while (true);
    }
    public String setPrefix(int index) {
        switch (index) {
            case -3: return "pL";
            case -2: return "nL";
            case -1: return "µL";
            default: return "mL";
            case 1: return "L";
            case 2: return "kL";
            case 3: return "ML";
            case 4: return "GL";
        }
    }

    // Print out text bottle
    public String print() {

        String bottlePicture = "";
        int topLine, bottomLine;
        int pictureNumber = (int) Math.ceil(this.percentFilled / 6.0);

        if (pictureNumber == 0) { pictureNumber = 1; }

        topLine = 21 * pictureNumber - 20;
        bottomLine = pictureNumber * 21 - 1;

        for (int i=topLine; i<=bottomLine; i++) {
            bottlePicture += FileRead(i);
            bottlePicture += "\n";
        }

        return bottlePicture;
    }


    // Reads line n from the file and returns as string
    private String FileRead(int n) {
        String line = "";

        try{
            line = Files.readAllLines(Paths.get("src/bottleImages")).get(n);
        }
        catch(IOException e){
            System.out.println(e);
        }

        return line;
    }

}
