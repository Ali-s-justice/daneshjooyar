import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CharInputReader {
    public static void main(String[] args) {
        String nestedArrayListAsString = "[[g, moadelat, 10:00], [g, moadelat, 11:00]";
        ArrayList<ArrayList<String>> arrayList = stringToArrayOfArrayList(nestedArrayListAsString);
        System.out.println(arrayList.getFirst());
        System.out.println(arrayList.getLast());
    }
    public static ArrayList<ArrayList<String>> stringToArrayOfArrayList(String string){
        ArrayList<ArrayList<String>> returnArraylist = new ArrayList<>();
        for (String innerListString : string.substring(1, string.length() - 1).split("], ")) {
            String[] elements = innerListString.substring(1).split(", ");
            ArrayList<String> innerList = new ArrayList<>(Arrays.asList(elements));
            returnArraylist.add(innerList);
        }
        return returnArraylist;
    }
}