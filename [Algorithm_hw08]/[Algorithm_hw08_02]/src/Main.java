import java.io.IOException;

/**
 * Created by yoonjae on 11/11/2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Huffman.encoding("data10.txt", "hw08_01_201202154_table.txt", "hw08_01_201202154_encoded.txt");
        Huffman.decoding("data10_table.txt", "data10_encoded.txt", "hw08_01_201202154_decoded.txt");
    }
}
