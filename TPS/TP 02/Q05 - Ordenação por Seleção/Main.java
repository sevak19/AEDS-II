import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;


public class Main {
    public static void main(String[] args) {
        long start = System.nanoTime();
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

        int mov = 0;
        int comp = 0;

        for (int i = 0; i < (entrada.size() - 1); i++) {
			int menor = i;
			for (int j = (i + 1); j < entrada.size(); j++){
				comp++;
                if (entrada.get(menor).getName().compareTo(entrada.get(j).getName()) > 0){
					menor = j;
				}
			}
			Pokemon temp = entrada.get(i);
            entrada.set(i, entrada.get(menor));
            entrada.set(menor, temp);
            mov += 3;
		}

        for(Pokemon pkmn : entrada) {
            pkmn.imprimir();
        }

        long end = System.nanoTime();

        double executionTime = (end - start);

        String conteudo = "1509171" + "\t" + comp + "comp\t" + mov + "mov\t" + executionTime + "ms";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("1509171_selecao.txt"))) {
            writer.write(conteudo);
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }

        sc.close();
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