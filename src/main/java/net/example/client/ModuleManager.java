package net.example.client;

import java.util.HashMap;
import java.util.Map;

public class ModuleManager {
    private static final Map<String, Boolean> modules = new HashMap<>();

    static {
        modules.put("Flight", false);
    }

    public static boolean isEnabled(String moduleName) {
        return modules.getOrDefault(moduleName, false);
    }

    public static void toggle(String moduleName) {
        boolean state = isEnabled(moduleName);
        modules.put(moduleName, !state);
    }
}
