import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;


public class Main {
    static int comp = 0;
    static int mov = 0;
    public static void main(String[] args) {
        String path = "/tmp/pokemon.csv";

        PokemonReader reader = new PokemonReader(path);
        ArrayList<Pokemon> pokedex = reader.readPokemons();

        ListaDupla entrada = new ListaDupla();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while(!input.equals("FIM")) {
            int number = Integer.parseInt(input);

            entrada.inserirFim(pokedex.get(number - 1));

            input = sc.nextLine();
        }

        long start = System.nanoTime();

        Sort.sort(entrada);

        long end = System.nanoTime();
        double executionTime = (end - start);

        entrada.mostrar();


        String conteudo = "1509171" + "\t" + comp + "comp\t" + mov + "mov\t" + executionTime + "ms";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("1509171_quicksort2.txt"))) {
            writer.write(conteudo);
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }

        sc.close();
    }
}

class CelulaDupla {
	public Pokemon elemento;
	public CelulaDupla ant;
	public CelulaDupla prox;

    public CelulaDupla() {
		this.elemento = null;
		this.ant = this.prox = null;
	}

	public CelulaDupla(Pokemon elemento) {
		this.elemento = elemento;
		this.ant = this.prox = null;
	}
}

class ListaDupla {
	public CelulaDupla primeiro;
	public CelulaDupla ultimo;

	public ListaDupla() {
		primeiro = new CelulaDupla();
		ultimo = primeiro;
	}

	public void inserirInicio(Pokemon x) {
		CelulaDupla tmp = new CelulaDupla(x);

        tmp.ant = primeiro;
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if(primeiro == ultimo){
            ultimo = tmp;
        }else{
            tmp.prox.ant = tmp;
        }
        tmp = null;
	}

	public void inserirFim(Pokemon x) {
        ultimo.prox = new CelulaDupla(x);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
    }

	public Pokemon removerInicio() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		}

        CelulaDupla tmp = primeiro;
        primeiro = primeiro.prox;
        Pokemon resp = primeiro.elemento;
        tmp.prox = primeiro.ant = null;
        tmp = null;
        return resp;
	}

    public Pokemon removerFim() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");
        } 
        Pokemon resp = ultimo.elemento;
        ultimo = ultimo.ant;
        ultimo.prox.ant = null;
        ultimo.prox = null;
        return resp;
	}

    public void inserir(Pokemon x, int pos) throws Exception {

        int tamanho = tamanho();

        if(pos < 0 || pos > tamanho){
                throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
        } else if (pos == 0){
            inserirInicio(x);
        } else if (pos == tamanho){
            inserirFim(x);
        } else {
            // Caminhar ate a posicao anterior a insercao
            CelulaDupla i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);
            
            CelulaDupla tmp = new CelulaDupla(x);
            tmp.ant = i;
            tmp.prox = i.prox;
            tmp.ant.prox = tmp.prox.ant = tmp;
            tmp = i = null;
        }
    }

	public Pokemon remover(int pos) throws Exception {
        Pokemon resp;
        int tamanho = tamanho();

        if (primeiro == ultimo){
            
            throw new Exception("Erro ao remover (vazia)!");
            
        }else if(pos < 0 || pos >= tamanho){
            
            throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");

        } else if (pos == 0){
            resp = removerInicio();
        } else if (pos == tamanho - 1){
            resp = removerFim();
        } else {
            // Caminhar ate a posicao anterior a insercao
            CelulaDupla i = primeiro.prox;
            for(int j = 0; j < pos; j++, i = i.prox);
            
            i.ant.prox = i.prox;
            i.prox.ant = i.ant;
            resp = i.elemento;
            i.prox = i.ant = null;
            i = null;
        }

        return resp;
    }

    public void mostrar() {
        for (CelulaDupla i = primeiro; i != null; i = i.prox) {
            if (i.elemento != null) {
                i.elemento.imprimir();
            }
        }
    }

    public void mostrarInverso() {
        for (CelulaDupla i = ultimo; i != primeiro; i = i.ant){
            i.elemento.imprimir();
        } 
    }

	public boolean pesquisar(Pokemon x) {
		boolean resp = false;
		for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
         if(i.elemento.getId() == x.getId()){
            resp = true;
            i = ultimo;
         }
		}
		return resp;
	}

    public int tamanho() {
        int tamanho = 0; 
        for(CelulaDupla i = primeiro; i != ultimo; i = i.prox, tamanho++);
        return tamanho;
    }
}

class Sort {
    public static int comp = 0;
    public static void sort(ListaDupla lista) {
        quicksort(lista.primeiro, lista.ultimo);
    }

    private static void quicksort(CelulaDupla esq, CelulaDupla dir) {
        if (dir != null && esq != dir && esq != dir.prox) {
        
            CelulaDupla i = esq.ant;
            CelulaDupla j;
            Pokemon pivo = dir.elemento;
    
            for(j = esq ; j != dir ; j = j.prox) {
                if (j != null && j.elemento != null) {
                    comp++;
                    if ((j.elemento.getGeneration() < pivo.getGeneration()) || (j.elemento.getGeneration() == pivo.getGeneration() && j.elemento.getName().compareTo(pivo.getName()) < 0)) {
                        if (i == null) {
                            i = esq;
                        } else {
                            i = i.prox;
                        }
                        if (i != j) {
                            swap(i, j);
                        }
                    } 
                }
            }
            if (i == null) {
                i = esq;
            } else {
                i = i.prox;
            }
            if (i != dir) {
                swap(i, dir);
            }
            quicksort(esq, i.ant);
            quicksort(i.prox, dir);
        }
    }

    private static void swap(CelulaDupla A, CelulaDupla B){
        if (A != null && B != null) {
            Pokemon temp = A.elemento;
            A.elemento = B.elemento;
            B.elemento = temp;
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

class PokemonReader {

    private String filePath;

    public PokemonReader(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Pokemon> readPokemons() {
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        
        try {
            Scanner sc = new Scanner(new File(filePath));
            sc.nextLine();

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (!line.isEmpty()) {
                    pokemons.add(new Pokemon(line));
                }
            }
            sc.close();
        }

        catch (FileNotFoundException e) { System.err.println("Arquivo não encontrado: " + filePath); }
        catch (Exception e) { System.err.println("Erro ao ler o arquivo: " + e.getMessage()); }
        
        return pokemons;
    }
}