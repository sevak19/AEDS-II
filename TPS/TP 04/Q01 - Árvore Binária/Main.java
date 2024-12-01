import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;


public class Main {
    public static void main(String[] args) {
        String path = "/tmp/pokemon.csv";
        ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>();

        try {
            Scanner sc = new Scanner(new File(path));
            sc.nextLine();
        while (sc.hasNextLine()) {
            pokedex.add(new Pokemon(sc.nextLine()));
        }
        sc.close();
        } catch (Exception e) { System.out.println("error");}

        Scanner sc = new Scanner(System.in);

        ArvoreBinaria pkmns = new ArvoreBinaria();

        String input = sc.nextLine();
        while(!input.equals("FIM")) {
            int number = Integer.parseInt(input);
            try {
                pkmns.inserir(pokedex.get(number - 1));
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
            input = sc.nextLine();
        }

        String nome = sc.nextLine();
        while(!nome.equals("FIM")) {
            System.out.println(nome);
            if(pkmns.pesquisar(nome)) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }
            nome = sc.nextLine();
        }

        sc.close();
    }
}

class No {
    public Pokemon elemento;
    public No esq, dir;

    public No(Pokemon elemento) {
        this(elemento, null, null);
    }

    public No(Pokemon elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

class ArvoreBinaria {
	private No raiz;

	public ArvoreBinaria() {
		raiz = null;
	}

	public boolean pesquisar(String x) {
		System.out.print("=>raiz ");
        return pesquisar(x, raiz);
	}

	private boolean pesquisar(String x, No i) {
      boolean resp;
		if (i == null) {
         resp = false;

      } else if (x.equals(i.elemento.getName())) {
         resp = true;

      } else if (x.compareTo(i.elemento.getName()) < 0) {
        System.out.print("esq ");
        resp = pesquisar(x, i.esq);

      } else {
        System.out.print("dir ");
        resp = pesquisar(x, i.dir);
      }
      return resp;
	}

	public void caminharCentral() {
		System.out.print("[ ");
		caminharCentral(raiz);
		System.out.println("]");
	}

	private void caminharCentral(No i) {
		if (i != null) {
			caminharCentral(i.esq);
			System.out.print(i.elemento.getName() + " ");
			caminharCentral(i.dir);
		}
	}

	public void caminharPre() {
		System.out.print("[ ");
		caminharPre(raiz);
		System.out.println("]");
	}

	private void caminharPre(No i) {
		if (i != null) {
			System.out.print(i.elemento.getName() + " ");
			caminharPre(i.esq);
			caminharPre(i.dir);
		}
	}

	public void caminharPos() {
		System.out.print("[ ");
		caminharPos(raiz);
		System.out.println("]");
	}

	private void caminharPos(No i) {
		if (i != null) {
			caminharPos(i.esq);
			caminharPos(i.dir);
			System.out.print(i.elemento.getName() + " ");
		}
	}

	public void inserir(Pokemon x) throws Exception {
		raiz = inserir(x, raiz);
	}

	private No inserir(Pokemon x, No i) throws Exception {
		if (i == null) {
         i = new No(x);

      } else if (x.getName().compareTo(i.elemento.getName()) < 0) {
         i.esq = inserir(x, i.esq);

      } else if (x.getName().compareTo(i.elemento.getName()) > 0) {
         i.dir = inserir(x, i.dir);

      } else {
         throw new Exception("Erro ao inserir!");
      }

		return i;
	}

	public void inserirPai(Pokemon x) throws Exception {
      if(raiz == null){
         raiz = new No(x);
      } else if(x.getId() < raiz.elemento.getId()){
		   inserirPai(x, raiz.esq, raiz);
      } else if(x.getId() > raiz.elemento.getId()){
		   inserirPai(x, raiz.dir, raiz);
      } else {
         throw new Exception("Erro ao inserirPai!");
      }
	}

	private void inserirPai(Pokemon x, No i, No pai) throws Exception {
		if (i == null) {
         if(x.getId() < pai.elemento.getId()){
            pai.esq = new No(x);
         } else {
            pai.dir = new No(x);
         }
      } else if (x.getId() < i.elemento.getId()) {
         inserirPai(x, i.esq, i);
      } else if (x.getId() > i.elemento.getId()) {
         inserirPai(x, i.dir, i);
      } else {
         throw new Exception("Erro ao inserirPai!");
      }
	}

	public void remover(Pokemon x) throws Exception {
		raiz = remover(x, raiz);
	}

	private No remover(Pokemon x, No i) throws Exception {

		if (i == null) {
         throw new Exception("Erro ao remover!");

      } else if (x.getId() < i.elemento.getId()) {
         i.esq = remover(x, i.esq);

      } else if (x.getId() > i.elemento.getId()) {
         i.dir = remover(x, i.dir);

      // Sem no a direita.
      } else if (i.dir == null) {
         i = i.esq;

      // Sem no a esquerda.
      } else if (i.esq == null) {
         i = i.dir;

      // No a esquerda e no a direita.
      } else {
         i.esq = maiorEsq(i, i.esq);
		}

		return i;
	}

	private No maiorEsq(No i, No j) {

      // Encontrou o maximo da subarvore esquerda.
		if (j.dir == null) {
			i.elemento = j.elemento; // Substitui i por j.
			j = j.esq; // Substitui j por j.ESQ.

      // Existe no a direita.
		} else {
         // Caminha para direita.
			j.dir = maiorEsq(i, j.dir);
		}
		return j;
	}

   public Pokemon getMaior(){
      Pokemon resp = new Pokemon();

      if(raiz != null){
         No i;
         for(i = raiz; i.dir != null; i = i.dir);
         resp = i.elemento;
      }

      return resp;
   }

   public Pokemon getMenor(){
      Pokemon resp = new Pokemon();

      if(raiz != null){
         No i;
         for(i = raiz; i.esq != null; i = i.esq);
         resp = i.elemento;
      }

      return resp;
   }

   public int getAltura(){
      return getAltura(raiz, 0);
   }

   public int getAltura(No i, int altura){
      if(i == null){
         altura--;
      } else {
         int alturaEsq = getAltura(i.esq, altura + 1);
         int alturaDir = getAltura(i.dir, altura + 1);
         altura = (alturaEsq > alturaDir) ? alturaEsq : alturaDir;
      }
      return altura;
   }

	public void remover2(Pokemon x) throws Exception {
      if (raiz == null) {
         throw new Exception("Erro ao remover2!");
      } else if(x.getId() < raiz.elemento.getId()){
         remover2(x, raiz.esq, raiz);
      } else if (x.getId() > raiz.elemento.getId()){
         remover2(x, raiz.dir, raiz);
      } else if (raiz.dir == null) {
         raiz = raiz.esq;
      } else if (raiz.esq == null) {
         raiz = raiz.dir;
      } else {
         raiz.esq = maiorEsq(raiz, raiz.esq);
      }
   }

	private void remover2(Pokemon x, No i, No pai) throws Exception {
		if (i == null) {
         throw new Exception("Erro ao remover2!");
      } else if (x.getId() < i.elemento.getId()) {
         remover2(x, i.esq, i);
      } else if (x.getId() > i.elemento.getId()) {
         remover2(x, i.dir, i);
      } else if (i.dir == null) {
         pai = i.esq;
      } else if (i.esq == null) {
         pai = i.dir;
      } else {
         i.esq = maiorEsq(i, i.esq);
		}
	}

   public Pokemon getRaiz() throws Exception {
      return raiz.elemento;
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