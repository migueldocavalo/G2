import java.util.ArrayList;
import java.util.Scanner;

// Interface
interface Exibivel {
    void exibirDados();
}

// Classe abstrata
abstract class Veiculo implements Exibivel {
    private String placa;
    private String modelo;
    private int ano;

    public Veiculo(String placa, String modelo, int ano) {
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
    }

    public String getPlaca() { return placa; }
    public String getModelo() { return modelo; }
    public int getAno() { return ano; }

    public void setPlaca(String placa) { this.placa = placa; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setAno(int ano) { this.ano = ano; }
}

// Subclasse Carro
class Carro extends Veiculo {
    private int qtdPortas;

    public Carro(String placa, String modelo, int ano, int qtdPortas) {
        super(placa, modelo, ano);
        this.qtdPortas = qtdPortas;
    }

    public void exibirDados() {
        System.out.println("Carro - Modelo: " + getModelo() +
                " | Placa: " + getPlaca() +
                " | Ano: " + getAno() +
                " | Portas: " + qtdPortas);
    }
}

// Subclasse Moto
class Moto extends Veiculo {
    private int cilindrada;

    public Moto(String placa, String modelo, int ano, int cilindrada) {
        super(placa, modelo, ano);
        this.cilindrada = cilindrada;
    }

    public void exibirDados() {
        System.out.println("Moto - Modelo: " + getModelo() +
                " | Placa: " + getPlaca() +
                " | Ano: " + getAno() +
                " | Cilindrada: " + cilindrada + "cc");
    }
}

// Subclasse Caminhao
class Caminhao extends Veiculo {
    private double capacidadeCarga;

    public Caminhao(String placa, String modelo, int ano, double capacidadeCarga) {
        super(placa, modelo, ano);
        this.capacidadeCarga = capacidadeCarga;
    }

    public void exibirDados() {
        System.out.println("Caminhão - Modelo: " + getModelo() +
                " | Placa: " + getPlaca() +
                " | Ano: " + getAno() +
                " | Capacidade de carga: " + capacidadeCarga + " toneladas");
    }
}

// Classe principal
public class CadastroVeiculos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Veiculo> lista = new ArrayList<>();
        int opcao;

        do {
            System.out.println("\n1 - Cadastrar novo veículo");
            System.out.println("2 - Listar veículos");
            System.out.println("3 - Sair");
            System.out.print(">> ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Tipo (1-Carro, 2-Moto, 3-Caminhão): ");
                    int tipo = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Modelo: ");
                    String modelo = scanner.nextLine();
                    System.out.print("Placa: ");
                    String placa = scanner.nextLine();
                    System.out.print("Ano: ");
                    int ano = scanner.nextInt();

                    if (tipo == 1) {
                        System.out.print("Quantidade de portas: ");
                        int portas = scanner.nextInt();
                        lista.add(new Carro(placa, modelo, ano, portas));
                    } else if (tipo == 2) {
                        System.out.print("Cilindrada: ");
                        int cilindrada = scanner.nextInt();
                        lista.add(new Moto(placa, modelo, ano, cilindrada));
                    } else if (tipo == 3) {
                        System.out.print("Capacidade de carga (toneladas): ");
                        double carga = scanner.nextDouble();
                        lista.add(new Caminhao(placa, modelo, ano, carga));
                    } else {
                        System.out.println("Tipo inválido.");
                    }
                    break;

                case 2:
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum veículo cadastrado.");
                    } else {
                        for (Veiculo v : lista) {
                            v.exibirDados();
                        }
                    }
                    break;

                case 3:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 3);

        scanner.close();
    }
}
