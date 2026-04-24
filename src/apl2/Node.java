// arquivo: src/apl2/Node.java

//INTEGRANTES:
//Matheus Medeiros, RA: 10748040
//Pedro Botelho, RA: 10738317
//Daniel Arais, RA: 10419718

package apl2;

// -- A classe Node (que pertence ao pacote apl2) deve conter os atributos que
// representam a nova versão dos dados de uma pessoa, conforme descrito no
// enunciado da atividade Apl2.
// -- A classe deve conter os construtores apropriados, assim como os métodos
// getters e setters.
// -- A classe também representa um nó que é usado na implementação da lista
// duplamente encadeada (classe DLinkedList).
// -- A classe deve sobrescrever (override) o método public String toString()
// {...}, retornando uma string com os valores dos atributos da classe.

public class Node {
    private String idPessoa;
    private String nomePessoa;
    private Float notaPessoa;
    private Node previous;
    private Node next;
    
    public Node() {}
    
    public Node(String idPessoa, String nomePessoa, Float notaPessoa, Node previous, Node next) {
        this.idPessoa = idPessoa;
        this.nomePessoa = nomePessoa;
        setNotaPessoa(notaPessoa);
        this.previous = previous;
        this.next = next;
    }

    public String getIdPessoa() {
        return idPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public Float getNotaPessoa() {
        return notaPessoa;
    }

    public Node getPrevious() {
        return previous;
    }

    public Node getNext() {
        return next;
    }

    public void setIdPessoa(String idPessoa) {
        this.idPessoa = idPessoa;
    }

    public void setNomePessoa(String nomePessoa){
        this.nomePessoa = nomePessoa;
    }

    public void setNotaPessoa(Float notaPessoa) {
        this.notaPessoa = (notaPessoa == null) ? 99.9f : notaPessoa;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "[dados: (" + id + ";" + nome + ";" + inteiro + ";" + decimo + ") | next: " + next + "]";
    } 
}