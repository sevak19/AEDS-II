import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;


public class Main {
    static int comp = 0;
    static int mov = 0;
    public static void main(String[] args) {
        String path = "/tmp/pokemon.csv";
        ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();

        try {
            Scanner sc = new Scanner(new File(path));
            sc.nextLine();
        while (sc.hasNextLine()) {
            pokemons.add(new Pokemon(sc.nextLine()));
        }
        sc.close();
        } catch (Exception e) { System.out.println("error");}

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        ArrayList<Pokemon> entrada = new ArrayList<Pokemon>();
        while(!input.equals("FIM")) {
            int number = Integer.parseInt(input);
            entrada.add(pokemons.get(number - 1));
            input = sc.nextLine();
        }

        long start = System.nanoTime();

        Sort.sort(entrada);

        long end = System.nanoTime();

        for(Pokemon pkmn : entrada) {
            pkmn.imprimir();
        }


        double executionTime = (end - start);

        String conteudo = "1509171" + "\t" + comp + "comp\t" + mov + "mov\t" + executionTime + "ms";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("1509171_mergesort.txt"))) {
            writer.write(conteudo);
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }

        sc.close();
    }
}

class Sort {
    public static void sort(ArrayList<Pokemon> lista) {
        mergesort(0, lista.size() - 1, lista);
    }

    private static void mergesort(int esq, int dir, ArrayList<Pokemon> lista) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergesort(esq, meio, lista);
            mergesort(meio + 1, dir, lista);
            intercalar(esq, meio, dir, lista);
        }
    }

    private static void intercalar(int esq, int meio, int dir, ArrayList<Pokemon> lista) {
        int n1 = meio - esq + 1;
        int n2 = dir - meio;

        ArrayList<Pokemon> a1 = new ArrayList<>(n1 + 1);
        ArrayList<Pokemon> a2 = new ArrayList<>(n2 + 1);

        for (int i = 0; i < n1; i++) {
            a1.add(lista.get(esq + i));
        }

        for (int j = 0; j < n2; j++) {
            a2.add(lista.get(meio + j + 1));
        }

        int i = 0, j = 0, k = esq;
        while (i < n1 && j < n2) {
            int compareFirstType = a1.get(i).getType(0).compareTo(a2.get(j).getType(0));
            Main.comp++;

            if (compareFirstType < 0) {
                lista.set(k, a1.get(i));
                i++;
                Main.mov++;
            } else if (compareFirstType == 0) {
                Main.comp++;
                if (a1.get(i).getName().compareTo(a2.get(j).getName()) <= 0) {
                    lista.set(k, a1.get(i));
                    i++;
                    Main.mov++;
                } else {
                    lista.set(k, a2.get(j));
                    j++;
                    Main.mov++;
                }
            } else {
                lista.set(k, a2.get(j));
                j++;
                Main.mov++;
            }
            k++;
        }

        while (i < n1) {
            lista.set(k, a1.get(i));
            i++;
            k++;
            Main.mov++;
        }

        while (j < n2) {
            lista.set(k, a2.get(j));
            j++;
            k++;
            Main.mov++;
        }
    }
}

class Pokemon {
    private int id;
    private int generation;
    private String name;
    private String description;
    private ArrayList<String> types;
    private ArrayList<String> abilities;
    private double weight;
    private double height;
    private int captureRate;
    private boolean isLegendary;
    private LocalDate captureDate;

    public Pokemon () { }

    public Pokemon (int id,
                   int generation,
                   String name,
                   String description,
                   ArrayList<String> types,
                   ArrayList<String> abilities,
                   double weight,
                   double heigth,
                   int captureRate,
                   boolean isLegendary,
                   LocalDate captureDate
                   ) {
        setId(id);
        setGeneration(generation);
        setName(name);
        setDescription(description);
        setTypes(types);
        setAbilities(abilities);
        setWeight(weight);
        setHeight(height);
        setCaptureRate(captureRate);
        setIsLegendary(isLegendary);
        setCaptureDate(captureDate);
    }

    public Pokemon (String linha) {
        String[] item = linha.split(",");

        setId(Integer.parseInt(item[0]));

        setGeneration(Integer.parseInt(item[1]));

        setName(item[2]);

        setDescription(item[3]);

        ArrayList<String> types = new ArrayList<>();
        types.add(item[4]);
        if (!item[5].isEmpty())
            types.add(item[5]);
        setTypes(types);

        int counter = 6;
        boolean IsDouble = false;
        ArrayList<String> abilities = new ArrayList<>();
        while (!IsDouble) {
            try {
                Double.parseDouble(item[counter]); 
                IsDouble = true;
            } catch (NumberFormatException e) {
                if(!item[counter].isEmpty()){
                    String abilitie = item[counter].replaceAll("[\\[\\]'\"']", "").trim();
                    abilities.add(abilitie);
                    counter++;
                }else{
                    IsDouble = true;
                }
            }
        }
        setAbilities(abilities);

        if(!item[counter].isEmpty())
            setWeight(Double.parseDouble(item[counter]));
        counter++;

        if(!item[counter].isEmpty())
            setHeight(Double.parseDouble(item[counter]));
        counter++;

        setCaptureRate(Integer.parseInt(item[counter]));
        counter++;

        setIsLegendary(item[counter].equals("1"));
        counter++;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        setCaptureDate(LocalDate.parse(item[counter], formatter));
    }

    public Pokemon clone() {
        Pokemon clone = new Pokemon(getId(), getGeneration(), getName(), getDescription(), getTypes(), getAbilities(), getWeight(), getHeight(), getCaptureRate(), getIsLegendary(), getCaptureDate());
        return clone;
    }
 
    public void imprimir() {
        System.out.printf("[#" + id + " -> " + name + ": " + description + " - [");
        int counter = 1;
        for(String type : types) {
            if(counter == 1){
                System.out.printf("'" + type + "'");
                counter++;
            }else{
                System.out.printf(", '" + type + "'");
            }
        }
        System.out.printf("] - [");
        counter = 1;
        for(String abilitie : abilities) {
            if(counter == 1){
                System.out.printf("'" + abilitie + "'");
                counter++;
            }else{
                System.out.printf(", '" + abilitie + "'");
            }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.printf("] - " + weight + "kg - " + height + "m - " + captureRate + "%% - " + isLegendary + " - " + generation + " gen] - " + captureDate.format(formatter));
        System.out.println();
    }

    public void setId (int id) {
        this.id = id;
    }
    public int getId () {
        return id;
    }

    public void setGeneration (int generation) {
        this.generation = generation;
    }
    public int getGeneration () {
        return generation;
    }

    public void setName (String name) {
        this.name = name;
    }
    public String getName () {
        return name;
    }

    public void setDescription (String description) {
        this.description = description;
    }
    public String getDescription () {
        return description;
    }

    public void setTypes (ArrayList<String> types) {
        this.types = types;
    }
    public ArrayList<String> getTypes () {
        return types;
    }
    public String getType (int index) {
        return types.get(index);
    }

    public void setAbilities (ArrayList<String> abilities) {
        this.abilities = abilities;
    }
    public ArrayList<String> getAbilities () {
        return abilities;
    }

    public void setWeight (double weight) {
        this.weight = weight;
    }
    public double getWeight () {
        return weight;
    }

    public void setHeight (double height) {
        this.height = height;
    }
    public double getHeight () {
        return height;
    }

    public void setCaptureRate (int captureRate) {
        this.captureRate = captureRate;
    }
    public int getCaptureRate () {
        return captureRate;
    }

    public void setIsLegendary (boolean isLegendary) {
        this.isLegendary = isLegendary;
    }
    public boolean getIsLegendary () {
        return isLegendary;
    }

    public void setCaptureDate(LocalDate captureDate) {
        this.captureDate = captureDate;
    }
    public LocalDate getCaptureDate() {
        return captureDate;
    }
}