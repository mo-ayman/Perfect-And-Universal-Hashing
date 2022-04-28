package IHashing;

import java.util.Arrays;
import java.util.Random;

public class UniversalHashFnGenerator {
    private int u_keyDimension;
    private int b_locationDimension;
    private Byte[][] universalHashFn;
    private int[] keys;
    private Byte[] locations;

    public UniversalHashFnGenerator() {
        this.u_keyDimension = 32;
        this.b_locationDimension = 0;
        this.keys = new int[0];
        this.locations = new Byte[0];
    }

    public void setRequisites(int u, int b, int[] keys){
        this.u_keyDimension = u;
        this.b_locationDimension = b;
        this.keys = keys;
        this.locations = new Byte[(int) Math.pow(2, this.b_locationDimension)];
        this.universalHashFn = new Byte[b][u];
        Arrays.fill(this.locations, Byte.parseByte("0"));
    }

    public Byte[][] generateFn(boolean collisionsPermitted){
        boolean isThereCollisions = false;
        while (!isThereCollisions){
            Arrays.fill(this.locations, Byte.parseByte("0"));
            for(int i = 0; i < this.b_locationDimension; i++) {
                for (int j = 0; j < this.u_keyDimension; j++)
                    this.universalHashFn[i][j] = randomize();
            }
            isThereCollisions = testCollisions();
            if(collisionsPermitted) return this.universalHashFn;
        }
        return this.universalHashFn;
    }

    private Byte randomize(){
        String set = "01";
        Random random = new Random();
        int i = random.nextInt(0,2);
        return Byte.parseByte(String.valueOf(set.charAt(i)));
    }

    protected Byte[] decompose(int key){
        Byte[] keyVector = new Byte[this.u_keyDimension];
        int index = this.u_keyDimension - 1;
        while (key > 0 && index >= 0){
            keyVector[index--] = Byte.parseByte(Integer.toString(key % 2));
            key /= 2;
        }
        while (index >= 0) keyVector[index--] = 0;
        return keyVector;
    }

    protected int aggregate(Byte[] locationVector){
        int index = locationVector.length - 1;
        int start = index;
        int loc = 0;
        while (index >= 0){
            if(locationVector[index] == 1) loc += Math.pow(2, start - index);
            index--;
        }
        return loc;
    }

    private boolean testCollisions(){
        boolean noCollisionsFound = true;
        Byte[] locationVector = new Byte[this.b_locationDimension];
        Byte[] keyVector;
        if(this.keys.length == 0) return true;
        for (int key : this.keys) {
            Arrays.fill(locationVector, Byte.parseByte("0"));
            keyVector = decompose(key);
            for (int j = 0; j < this.u_keyDimension; j++) {
                if (keyVector[j] == 1) {
                    for (int k = 0; k < this.b_locationDimension; k++) {
                        int val = locationVector[k].intValue() ^ this.universalHashFn[k][j] ;
                        locationVector[k] = Byte.parseByte(Integer.toString(val));
                    }
                }
            }
            int loc = aggregate(locationVector);
            noCollisionsFound = noCollisionsFound && !(this.locations[loc] == 1);////there is collisions
            this.locations[loc] = 1;
        }
        return noCollisionsFound;
    }
}
