package css.cis3334.heartratetracker;
/**
 * Stores information for a single heart rate reading.
 *     Includes the person's age for calculation of activity level
 *
 * Created by Tom Gibbons in Feb 2017.
 * For the CIS 3334 class at St. Scholastica
 */

public class HeartRate {
    private Integer pulse;       // actual rate in beats per minute
    private Integer age;            // age when heart rate measurement was taken
    private Double maxHeartRate;    // calculated maximum rate based on age
    private Double percent;         // percent of maximum rate
    private Integer range;          // which range this heart rate is in, used as index into arrays below

    String rangeNames[] = {"Resting", "Moderate", "Endurance", "Aerobic","Anaerobic","Red zone"};
    String rangeDescriptions[] = {"In active or resting", "Weight maintenance and warm up", "Fitness and fat burning", "Cardio training and endurance","Hardcore interval training","Maximum Effort"};
    Double rangeBounds[] = {.50, .60, .70, .80, .90, 1.00};
    String rangeColor[];

    public HeartRate(Integer heartRate, Integer age) {
        this.pulse = heartRate;
        this.age = age;
        calcHeartRange(age);
    }

    public Integer calcHeartRange(Integer age) {
        maxHeartRate = 220.0 - age;        // from  http://www.cdc.gov/physicalactivity/basics/measuring/heartrate.htm
        percent = pulse /maxHeartRate;
        for (int i=0; i<rangeNames.length; i++) {
            if ( percent < rangeBounds[i] ) {
                // heartrate is in this range
                range = i;
                return range;          // break out of this loop
            }
        }
        return rangeNames.length-1;                      // this should never happen
    }

    public Integer getRange() {
        calcHeartRange(age);
        return range;
    }

    public String getRangeName() {
        calcHeartRange(age);
        return rangeNames[range];
    }

    public String getRangeDescrtiption() {
        calcHeartRange(age);
        return rangeDescriptions[range];
    }

    /**
     * This is called to set the background color for the given range
     *
     * @return int
     */
    public int getRangeColor() {
        calcHeartRange(age);
        int retcol = 0;

        switch (range) {
            case 0:retcol = R.color.blue;
                break;
            case 1:retcol = R.color.green;
                break;
            case 2:retcol = R.color.yellow;
                break;
            case 3:retcol = R.color.orange;
                break;
            case 4:retcol = R.color.red;
                break;
            case 5:retcol = R.color.white;
                break;
        }

        return retcol;
    }

    public Integer getPulse() {
        return pulse;
    }

    public void setPulse(Integer pulse) {
        this.pulse = pulse;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
        calcHeartRange(age);                // update the calculates which are based on the age
    }

    @Override
    public String toString() {
        return "HeartRate = " + pulse + " - " + getRangeName();
    }
}
