package eu.andrealeet.it.test.listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

import eu.andrealeet.it.test.HackClient;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.util.crash.CrashReportSection;

public final class EventManager {
    
    private final HashMap<Class<? extends Listener>, ArrayList<? extends Listener>> listenerMap = new HashMap<>();

	public EventManager(HackClient client) {
    }

    public <L extends Listener, E extends Event<L>> void fire(E event) {
        try {
            Class<L> type = event.getListenerType();

            @SuppressWarnings("unchecked")
            ArrayList<L> listeners = (ArrayList<L>)listenerMap.get(type);

            if(listeners == null || listeners.isEmpty())
                return;
            
            // Evitiamo problemi con la modifica concorrente
            ArrayList<L> listeners2 = new ArrayList<>(listeners);

            // remove() imposta un elemento a null prima di rimuoverlo, se
            // un thread chiama remove() mentre un altro chiama fire() è possibile
            // che la lista contenga dei valori null che vanno filtrati
            listeners.removeIf(Objects::isNull);

            event.fire(listeners2);

        } catch(Throwable e) {
            e.printStackTrace();

            CrashReport report = CrashReport.create(e, "fire() event");
            CrashReportSection section = report.addElement("Evento affetto: ");
            section.add("Event class", () -> event.getClass().getName());

            throw new CrashException(report);
        }
    }

    public <L extends Listener> void add(Class<L> type, L listener) {
		try {
			@SuppressWarnings("unchecked")
			ArrayList<L> listeners = (ArrayList<L>)listenerMap.get(type);
			
			if(listeners == null) {
				listeners = new ArrayList<>(Arrays.asList(listener));
				listenerMap.put(type, listeners);
				return;
			}
			
			listeners.add(listener);
			
		} catch(Throwable e) {
			e.printStackTrace();
			
			CrashReport report = CrashReport.create(e, "add() Event Listener");
			CrashReportSection section = report.addElement("Listener affetto");
			section.add("Listener type", () -> type.getName());
			section.add("Listener class", () -> listener.getClass().getName());
			
			throw new CrashException(report);
		}
	}
	
	public <L extends Listener> void remove(Class<L> type, L listener) {
		try {
			@SuppressWarnings("unchecked")
			ArrayList<L> listeners = (ArrayList<L>)listenerMap.get(type);
			
			if(listeners != null)
				listeners.remove(listener);
			
		} catch(Throwable e) {
			e.printStackTrace();
			
			CrashReport report = CrashReport.create(e, "remove() Event listener");
			CrashReportSection section = report.addElement("Listener affetto");
			section.add("Listener type", () -> type.getName());
			section.add("Listener class", () -> listener.getClass().getName());
			
			throw new CrashException(report);
		}
	}


}