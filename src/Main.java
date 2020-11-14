import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;

class Asset {

    protected String name;
    protected String type;
    protected Integer shares = 0;
    protected String transactionType = null;
    protected Boolean flag = false;

    public Asset(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        if (transactionType == null)
            return (name + "," + type + "," + shares);
        else
            return (transactionType + "," + name + "," + type + "," + shares);
    }

    public Integer compareTo(Asset anotherAsset) {
        int comparison = this.getName().compareTo(anotherAsset.getName());
        if (comparison < 0) {
            return (-1);
        } else if (comparison > 0) {
            return 1;
        } else {
            comparison = this.getType().compareTo(anotherAsset.getType());
            return Integer.compare(comparison, 0);
        }
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public Integer getShares() {
        return this.shares;
    }

    public Boolean getFlag() {
        return this.flag;
    }

    public void setShares(Integer value) {
        this.shares = value;
    }

    public void setTransactionType(String value) {
        if (value.equals("BUY") || value.equals("SELL"))
            this.transactionType = value;
        else
            this.transactionType = null;
    }

    public void setFlag(Boolean value) {
        this.flag = value;
    }
}

public class Main {
    /**
     * Iterate through each line of input.
     */
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        while ((line = in.readLine()) != null) {
            Main.matchBenchmark(line);
        }
    }

    private static int partition(ArrayList<Asset> arr, int begin, int end) {
        Asset pivot = arr.get(arr.size() - 1);
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr.get(j).compareTo(pivot) <= 0) {
                i++;

                Collections.swap(arr, i, j);
            }
        }

        Collections.swap(arr, i + 1, end);

        return i + 1;
    }

    public static void quickSort(ArrayList<Asset> arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    public static void matchBenchmark(String input) {

        // Initialising ArrayLists to store the Assets
        ArrayList<Asset> portfolio = new ArrayList<>();
        ArrayList<Asset> benchmark = new ArrayList<>();
        ArrayList<Asset> transactions = new ArrayList<>();

        // Fetching and parsing data from the Input in the variables we declared
        String[] splitInput = input.split(":");
        String portfolioInput = splitInput[0];
        String benchmarkInput = splitInput[1];

        for (String s : portfolioInput.split("\\|")) {
            String[] splitPInput = s.split(",");
            Asset p = new Asset(splitPInput[0], splitPInput[1]);
            p.setShares(Integer.parseInt(splitPInput[2]));
            portfolio.add(p);
        }
        for (String s : benchmarkInput.split("\\|")) {
            String[] splitBInput = s.split(",");
            Asset b = new Asset(splitBInput[0], splitBInput[1]);
            b.setShares(Integer.parseInt(splitBInput[2]));
            benchmark.add(b);
        }

        // Summary: I check for the similar assets from my portfolio in benchmark
        // and make transactions accordingly to match the expectations set (in benchmark).
        // similar --> only assets with the same /name/ and /type/ will be compared for now!
        for (Asset p : portfolio)
            for (Asset b : benchmark)
                if ((p.getName().equals(b.getName())) && (p.getType().equals(b.getType()))) {
                    int diff = b.getShares() - p.getShares();
                    Asset temp = p;

                    if (diff > 0) {
                        temp.setTransactionType("BUY");
                        temp.setShares(diff);
                        transactions.add(temp);
                    } else if (diff < 0) {
                        temp.setTransactionType("SELL");
                        temp.setShares(Math.abs(diff));
                        transactions.add(temp);
                    }

                    p.setFlag(true);
                    b.setFlag(true);
                }


        // Removing 'redundant' entries [Assets] that
        // (1) either has been already added in transactions
        // and/or (2) are already present in both portfolio & benchmark
        portfolio.removeIf(Asset::getFlag);
        benchmark.removeIf(Asset::getFlag);

        // Selling portfolio's unneeded assets that could not be found in the benchmark
        for (Asset p : portfolio) {
            p.setTransactionType("SELL");
            transactions.add(p);
        }

        // Buying benchmark's so needed assets that were nowhere to be found in our portfolio
        for (Asset b : benchmark) {
            b.setTransactionType("BUY");
            transactions.add(b);
        }

        // Applying the QuickSort algorithm for transactions to sort them
        // in alphabetical order based on the names of the assets
        quickSort(transactions, 0, transactions.size() - 1);

        // Output. The End.
        for (Asset t : transactions)
            System.out.println(t.toString());
    }
}
