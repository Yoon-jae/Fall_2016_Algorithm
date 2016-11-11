import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by yoonjae on 11/11/2016.
 */
public class Huffman {

    private static ArrayList<BitCode> bitCodeTable = new ArrayList<>();

    public static void encoding(String dataFileName, String tableOutputFileName, String encodedOutputFileName) throws IOException {
        BufferedReader dataFile_br = new BufferedReader(new FileReader(dataFileName));
        BufferedWriter tableFile_bw = new BufferedWriter(new FileWriter(tableOutputFileName));
        BufferedWriter encodedFile_bw = new BufferedWriter(new FileWriter(encodedOutputFileName));

        int frequencyTable[] = new int[27];
        MinHeap minHeap = new MinHeap();
        String line;
        StringBuffer inputData = new StringBuffer();
        bitCodeTable.clear();

        estimateFrequency(dataFile_br, frequencyTable, inputData);
        addAllTrecordToMinHeap(frequencyTable, minHeap);
        Trecord huffmanTree = makeHuffmanTree(minHeap);
        makeHuffmanCode(huffmanTree, new StringBuffer());

        makeTableFile(tableFile_bw);
        makeEncodedFile(encodedFile_bw, inputData);


        System.out.println("\nEncoded Complete.");
        System.out.println("Input : " + dataFileName);
        System.out.println("Output : " + tableOutputFileName + ", " + encodedOutputFileName + "\n");
    }

    public static void decoding(String tableFileName, String encodedFileName, String decodedOutputFileName) throws IOException {
        BufferedReader tableFile_br = new BufferedReader(new FileReader(tableFileName));
        BufferedReader encodedFile_br = new BufferedReader(new FileReader(encodedFileName));
        BufferedWriter decodedFile_bw = new BufferedWriter(new FileWriter(decodedOutputFileName));

        String line;
        StringBuffer inputData = new StringBuffer();
        StringBuffer decodedData = new StringBuffer();
        Trecord[] huffmanTree = new Trecord[100000];
        bitCodeTable.clear();

        inputTableInformation(tableFile_br);
        makeHuffmanTree(huffmanTree);
        makeDecodedData(encodedFile_br, decodedData, huffmanTree);
        makeDecodedFile(decodedFile_bw, decodedData);

        System.out.println("\nDecoded Comelete.");
        System.out.println("Input : " + encodedFileName + ", " + tableFileName);
        System.out.println("Output : " + decodedOutputFileName + "\n");
    }

    private static void makeDecodedFile(BufferedWriter decodedFile_bw, StringBuffer decodedData) throws IOException {
        decodedFile_bw.write(decodedData.toString());
        decodedFile_bw.close();
    }

    private static void makeDecodedData(BufferedReader encodedFile_br, StringBuffer decodedData, Trecord[] huffmanTree) throws IOException {
        String line;
        while ((line = encodedFile_br.readLine()) != null) {
            int pointer = 1;
            for (int i = 0; i < line.length(); i++) {
                char bit = line.charAt(i);
                if(bit == '0')
                    pointer *= 2;
                else if(bit == '1')
                    pointer = pointer * 2 + 1;
                if(huffmanTree[pointer] != null) {
                    decodedData.append(huffmanTree[pointer].alpha);
                    pointer = 1;
                }
            }
            decodedData.append("\n");
        }
    }

    private static void makeHuffmanTree(Trecord[] huffmanTree) {
        for (BitCode b : bitCodeTable) {
            int pointer = 1;
            for (int i = 0; i < b.code.length(); i++) {
                if (b.code.charAt(i) == '0')
                    pointer *= 2;
                else if (b.code.charAt(i) == '1')
                    pointer = pointer * 2 + 1;
                if (i == b.code.length() - 1) {
                    huffmanTree[pointer] = new Trecord(b.alpha);
                }
            }
        }
    }

    private static void inputTableInformation(BufferedReader tableFile_br) throws IOException {
        String line;
        while ((line = tableFile_br.readLine()) != null) {
            StringTokenizer stk = new StringTokenizer(line, ",");
            char ch = stk.nextToken().charAt(0);
            StringBuffer bitCode = new StringBuffer(stk.nextToken());
            bitCodeTable.add(new BitCode(ch, bitCode));
        }
    }

    private static void addAllTrecordToMinHeap(int[] frequencyTable, MinHeap minHeap) {
        for (int i = 1; i <= 26; i++)
            if (frequencyTable[i] != 0)
                minHeap.add(new Trecord(numToChar(i), frequencyTable[i]));
    }

    private static void estimateFrequency(BufferedReader dataFile_br, int[] frequencyTable, StringBuffer inputData) throws IOException {
        String line;
        while ((line = dataFile_br.readLine()) != null) {
            inputData.append(line);
            for (int i = 0; i < line.length(); i++)
                frequencyTable[charToNum(line.charAt(i))]++;
        }
        dataFile_br.close();
    }

    private static Trecord makeHuffmanTree(MinHeap minHeap) {
        while (!(minHeap.size() == 1)) {
            Trecord tNode = new Trecord();
            tNode.left = minHeap.poll();
            tNode.right = minHeap.poll();
            tNode.freq = tNode.left.freq + tNode.right.freq;
            minHeap.add(tNode);
        }

        return minHeap.poll();
    }

    private static void makeTableFile(BufferedWriter tableFile_bw) throws IOException {
        bitCodeTable.sort((b1, b2) -> Character.compare(b1.alpha, b2.alpha));
        for (BitCode b : bitCodeTable)
            tableFile_bw.write(b.toString() + "\n");
        tableFile_bw.close();
    }

    private static void makeEncodedFile(BufferedWriter encodedFile_bw, StringBuffer inputData) throws IOException {
        for (int i = 0; i < inputData.length(); i++) {
            for (BitCode b : bitCodeTable) {
                if (inputData.charAt(i) == b.alpha) {
                    encodedFile_bw.write(String.valueOf(b.code));
                    break;
                }
            }
        }
        encodedFile_bw.close();
    }

    private static void makeHuffmanCode(Trecord tNode, StringBuffer buf) {
        if (isLeafNode(tNode))
            bitCodeTable.add(new BitCode(tNode.alpha, buf));

        if (hasLeftChild(tNode))
            makeHuffmanCode(tNode.left, new StringBuffer(buf + "0"));

        if (hasRightChild(tNode))
            makeHuffmanCode(tNode.right, new StringBuffer(buf + "1"));
    }

    private static boolean hasRightChild(Trecord tNode) {
        return tNode.right != null;
    }

    private static boolean hasLeftChild(Trecord tNode) {
        return tNode.left != null;
    }

    private static boolean isLeafNode(Trecord tNode) {
        return tNode.left == null && tNode.right == null;
    }

    private static int charToNum(char alpha) {
        if (alpha == ' ') return 0;
        return alpha - 'a' + 1;
    }

    private static char numToChar(int num) {
        return (char) (num + 'a' - 1);
    }

}