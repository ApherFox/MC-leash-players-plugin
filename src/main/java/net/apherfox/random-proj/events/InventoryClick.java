package net.apherfox.random-proj.events;

import java.util.ArrayList;

public class InventoryClick implements Listener {
  @EventHandler
  public void onInventoryClick (InventoryClickEvent event) {
    ArrayList<GUI> later = new ArrayList();
    for (GUI gui : GUI.guis) {
      if (gui.inventory.equals(event.getInventory())) {
        later.add(gui);
      }
    }
    for (GUI gui : later) {
      gui.onInventoryClick(event);
    }
  }
}
