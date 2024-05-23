package servCommands;

import server.*;

import java.io.Serializable;

/**
 * Интерфейс всех для команд
 */

public interface Command extends Serializable {
    /**
    * метод, вызываемый у команд без передаваемых параметров(или параметров, передаваемых не в одной строке с командой)
    */
    default void execute(ProductCollection productCollection, Object[] p, Server server){
        System.out.println("ns d gbdt");
    };
}
