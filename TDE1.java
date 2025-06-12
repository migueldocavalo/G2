class Automovel {
    private String placa;
    private String modelo;
    private String marca;
    private int ano;
    private double valor;

    public Automovel(String placa, String modelo, String marca, int ano, double valor) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.valor = valor;
    }

    public String getPlaca() { return placa; }
    public String getModelo() { return modelo; }
    public String getMarca() { return marca; }
    public int getAno() { return ano; }
    public double getValor() { return valor; }

    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setAno(int ano) { this.ano = ano; }
    public void setValor(double valor) { this.valor = valor; }

    @Override
    public String toString() {
        return placa + "," + modelo + "," + marca + "," + ano + "," + valor;
    }

    public String formatado() {
        return "Placa: " + placa + ", Modelo: " + modelo + ", Marca: " + marca + ", Ano: " + ano + ", Valor: R$" + valor;
    }
}

public class CadastroAutomoveis {
    private static final String NOME_ARQUIVO = "automoveis.txt";
    private static ArrayList<Automovel> lista = new ArrayList<>();

    public static void main(String[] args) {
        carregarDados();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n1 - Incluir automóvel");
            System.out.println("2 - Remover automóvel");
            System.out.println("3 - Alterar dados de automóvel");
            System.out.println("4 - Consultar automóvel por placa");
            System.out.println("5 - Listar automóveis (ordenado)");
            System.out.println("6 - Salvar e sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: inserirAutomovel(scanner); break;
                case 2: removerAutomovel(scanner); break;
                case 3: alterarAutomovel(scanner); break;
                case 4: consultarAutomovel(scanner); break;
                case 5: listarAutomoveis(scanner); break;
                case 6: salvarDados(); System.out.println("Dados salvos. Saindo..."); break;
                default: System.out.println("Opção inválida.");
            }
        } while (opcao != 6);
    }

    private static void inserirAutomovel(Scanner sc) {
        System.out.print("Placa: ");
        String placa = sc.nextLine();
        if (buscarPorPlaca(placa) != null) {
            System.out.println("Já existe um automóvel com essa placa.");
            return;
        }

        System.out.print("Modelo: ");
        String modelo = sc.nextLine();
        System.out.print("Marca: ");
        String marca = sc.nextLine();
        System.out.print("Ano: ");
        int ano = sc.nextInt();
        System.out.print("Valor: ");
        double valor = sc.nextDouble();
        sc.nextLine();

        lista.add(new Automovel(placa, modelo, marca, ano, valor));
        System.out.println("Automóvel incluído.");
    }

    private static void removerAutomovel(Scanner sc) {
        System.out.print("Informe a placa do automóvel a remover: ");
        String placa = sc.nextLine();
        Automovel a = buscarPorPlaca(placa);
        if (a != null) {
            lista.remove(a);
            System.out.println("Automóvel removido.");
        } else {
            System.out.println("Automóvel não encontrado.");
        }
    }

    private static void alterarAutomovel(Scanner sc) {
        System.out.print("Placa do automóvel a alterar: ");
        String placa = sc.nextLine();
        Automovel a = buscarPorPlaca(placa);
        if (a == null) {
            System.out.println("Automóvel não encontrado.");
            return;
        }

        System.out.print("Novo modelo: ");
        a.setModelo(sc.nextLine());
        System.out.print("Nova marca: ");
        a.setMarca(sc.nextLine());
        System.out.print("Novo ano: ");
        a.setAno(sc.nextInt());
        System.out.print("Novo valor: ");
        a.setValor(sc.nextDouble());
        sc.nextLine();
        System.out.println("Automóvel alterado.");
    }

    private static void consultarAutomovel(Scanner sc) {
        System.out.print("Placa do automóvel: ");
        String placa = sc.nextLine();
        Automovel a = buscarPorPlaca(placa);
        if (a != null) {
            System.out.println(a.formatado());
        } else {
            System.out.println("Automóvel não encontrado.");
        }
    }

    private static void listarAutomoveis(Scanner sc) {
        if (lista.isEmpty()) {
            System.out.println("Nenhum automóvel cadastrado.");
            return;
        }

        System.out.print("Ordenar por (placa/modelo/marca): ");
        String criterio = sc.nextLine().toLowerCase();

        switch (criterio) {
            case "placa":
                lista.sort(Comparator.comparing(Automovel::getPlaca));
                break;
            case "modelo":
                lista.sort(Comparator.comparing(Automovel::getModelo));
                break;
            case "marca":
                lista.sort(Comparator.comparing(Automovel::getMarca));
                break;
            default:
                System.out.println("Critério inválido.");
                return;
        }

        for (Automovel a : lista) {
            System.out.println(a.formatado());
        }
    }

    private static Automovel buscarPorPlaca(String placa) {
        for (Automovel a : lista) {
            if (a.getPlaca().equalsIgnoreCase(placa)) {
                return a;
            }
        }
        return null;
    }

    private static void salvarDados() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
            for (Automovel a : lista) {
                writer.write(a.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    private static void carregarDados() {
        File arquivo = new File(NOME_ARQUIVO);
        if (!arquivo.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 5) {
                    String placa = partes[0];
                    String modelo = partes[1];
                    String marca = partes[2];
                    int ano = Integer.parseInt(partes[3]);
                    double valor = Double.parseDouble(partes[4]);
                    lista.add(new Automovel(placa, modelo, marca, ano, valor));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar os dados: " + e.getMessage());
        }
    }
}
