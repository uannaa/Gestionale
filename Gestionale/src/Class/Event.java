/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author paolo
 */
public class Event {
    
    private String nome_evento;
    private String descrizione;
    private Date data;
    private Categoria categoria;
    
    public Event(String nome, String descrizione, String data, String ora, Categoria cat) {
        this.nome_evento = nome;
        this.descrizione = descrizione;
        this.data = conversione(data, ora);
        this.categoria = categoria;
        
    }
    
    private Date conversione(String data, String ora) {
        String p2 = data + "/" + ora; //   03/12/2025/19/50
        
        String f[] = p2.split("/");
        int dateint[] = null;
        for (int i = 0; i < f.length; i++) {
            dateint[i] = Integer.parseInt(f[i]);
        }
        
        return new Date(dateint[2], dateint[1], dateint[0], dateint[3], dateint[4]);
    }

    public String getNome() {
        return nome_evento;
    }

    public void setNome(String nome_evento) {
        this.nome_evento = nome_evento;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    
    
}
