package com.drone.feeder.projetodronefeeder.controller;

import java.util.HashMap;

/** class mensagem. */
public class MensagemController {

  /** metodo . */
  public HashMap<String, String> mensagem() {
    HashMap<String, String> msg = new HashMap<String, String>();
    msg.put("Message", "Inserido com sucesso!");
    return msg;
  }

  /** metodo . */
  public HashMap<String, String> atualizar() {
    HashMap<String, String> msg = new HashMap<String, String>();
    msg.put("Message", "Atualizado com sucesso!");
    return msg;
  }

  /** metodo . */
  public HashMap<String, String> excluir() {
    HashMap<String, String> msg = new HashMap<String, String>();
    msg.put("Message", "Excluído com sucesso!");
    return msg;
  }
}
