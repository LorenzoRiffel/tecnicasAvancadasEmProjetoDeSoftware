package com.emilly.tema6;

import com.emilly.tema6.Contato;

import java.util.ArrayList;
import java.util.Arrays;

public class Agenda {

    List<Contato> agenda = new ArrayList<>();

    public void adicionarContato(Contato contato) {
      	if (contato.getNomeContato() != null && contato.getNomeContato() != "" && contato.getNumeroContato() != null && contato.getNumeroContato() != "" && contato.getIdContato() != null) {
            if (contato.getNumeroContato().matches("^[0-9]+$")) {
		agenda.add(contato);
		return true;
	    } else {
		return false;
	    }
	} else {
	  return false;
	}
    }

    public void removerContato(String nomeId) {

        Optional<Integer> i = verificaNumero(nomeId);

        i.ifPresentOrElse(
                (value)
                        ->
                {
                    Optional<Contato> c = buscarContatoPorId(value);
                    if (c.isPresent()) {
                        agenda.remove(c.get());
                        System.out.println("Removido!");

                    } else {
                        System.out.println("Este contato não existe!");

                    }
                },
                ()
                        ->
                {
                    Optional<Contato> c = buscarContatoPorNome(nomeId);
                    if (c.isPresent()) {
                        agenda.remove(c.get());
                        System.out.println("Removido!");

                    } else {
                        System.out.println("Este contato não existe!");

                    }
                }
        );

    }

    public boolean listarContatos() {
        if (agenda.size() != 0) {
            System.out.println("--------------- LISTA DE CONTATOS ---------------");
            for (int i = 0; i < agenda.size(); i++) {
                System.out.println(agenda.get(i).getIdContato() + " : " + agenda.get(i).getNomeContato());
                System.out.println(agenda.get(i).getNumeroContato());
            }
            return true;
        } else {
            System.out.println("Nenhum contato encontrado");
            return false;

        }

    }

    public ArrayList<Contato> getAgenda() {
            return agenda;

    }


    public Contato buscarContatoPorId(int id) {

        for (int i = 0; i < agenda.size(); i++) {

            if (agenda.get(i).getIdContato() == id) {
                return agenda.get(i);
            }

        }
        return null;
    }

    private Optional<Integer> verificaNumero(String texto) {

        if ((texto.matches("^[0-9]+$"))) {
            Integer inteiro = Integer.parseInt(texto);
	    return Optional.of(inteiro);
        } 
            return Optional.empty();
    }

    public Contato buscarContatoPorNome(String nome) {

        for (int i = 0; i < agenda.size(); i++) {
            if (agenda.get(i).getNomeContato().contains(nome)) {
                return agenda.get(i);
            }
        }
        return null;
    }
}