/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

/**
 *
 * @author paolo
 */
public class Categoria {
    
    private String nome_categoria;
    private String colore;
    
    public Categoria(String nome_categoria, String colore) {
        this.nome_categoria = nome_categoria;
        this.colore = colore;
    }
    
    public String getNome() {
        return nome_categoria;
    }

    public void setNome(String nome_categoria) {
        this.nome_categoria = nome_categoria;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }
    
}
