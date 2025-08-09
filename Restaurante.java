import java.util.*;

public class Restaurante {
    private static final String[] CARDAPIO = {
        "Pizza R$25.00",
        "Hambúrguer R$15.00",
        "Suco R$5.00",
        "Refrigerante R$6.00"
    };

    private static final double[] PRECOS = {25.00, 15.00, 5.00, 6.00};
    private static final double TAXA_SERVICO = 0.15;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            exibirMenuPrincipal();
            int escolha = obterInteiro("Escolha uma opção: ");

            switch (escolha) {
                case 1:
                    fazerPedido();
                    break;
                case 2:
                    System.out.println("Encerrando o programa. Obrigado!");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Fazer Pedido");
        System.out.println("2. Sair");
    }

    private static void fazerPedido() {
        System.out.print("\nDigite o nome do cliente: ");
        String nomeCliente = scanner.nextLine();

        List<Integer> itensEscolhidos = new ArrayList<>();
        exibirCardapio();

        while (true) {
            int item = obterInteiro("Digite o número do item (0 para finalizar): ");
            if (item == 0) break;
            if (item >= 1 && item <= CARDAPIO.length) {
                itensEscolhidos.add(item);
            } else {
                System.out.println("Item inválido.");
            }
        }

        if (itensEscolhidos.isEmpty()) {
            System.out.println("Nenhum item foi selecionado.");
            return;
        }

        gerarNotaFiscal(nomeCliente, itensEscolhidos);
    }

    private static void exibirCardapio() {
        System.out.println("\n=== CARDÁPIO ===");
        for (int i = 0; i < CARDAPIO.length; i++) {
            System.out.println((i + 1) + ". " + CARDAPIO[i]);
        }
    }

    private static void gerarNotaFiscal(String nomeCliente, List<Integer> pedido) {
        double subtotal = 0;

        System.out.println("\n=== NOTA FISCAL ===");
        System.out.println("Cliente: " + nomeCliente); 
        for (int item : pedido) {
            System.out.println(CARDAPIO[item - 1]); 
            subtotal += PRECOS[item - 1];
        }

        double taxa = subtotal * TAXA_SERVICO;
        double total = subtotal + taxa;

        System.out.printf("Subtotal: R$%.2f\n", subtotal);
        System.out.printf("Taxa de Serviço (15%%): R$%.2f\n", taxa);
        System.out.printf("Total a pagar: R$%.2f\n", total);

        double valorRecebido;
        do {
            valorRecebido = obterDouble("Informe o valor recebido em dinheiro: ");
            if (valorRecebido < total) {
                System.out.println("Valor insuficiente. Tente novamente.");
            }
        } while (valorRecebido < total);

        double troco = valorRecebido - total;
        System.out.printf("Troco: R$%.2f\n", troco);
    }

    private static int obterInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }

    private static double obterDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um valor numérico.");
            }
        }
    }
}
