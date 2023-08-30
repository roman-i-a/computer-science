package chapter3;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class CSP<V, D> {
    private List<V> variables;
    private Map<V, List<D>> domains;
    private Map<V, List<Constraint<V, D>>> constraints = new HashMap<>();

    public CSP(List<V> variables, Map<V, List<D>> domains) {
        this.variables = variables;
        this.domains = domains;
        for (V variable : variables) {
            constraints.put(variable, new ArrayList<>());
            if (!domains.containsKey(variable)) {
                throw new IllegalArgumentException("Every variable should have a domain assigned to it.");
            }
        }
    }

    public void addConstraint(Constraint<V, D> constraint) {
        for (V variable : constraint.variables) {
            if (!variables.contains(variable)) {
                throw new IllegalArgumentException("Variable in cosntraints not in CSP.");
            }
            constraints.get(variable).add(constraint);
        }
    }

    // Проверка присвоений на противоречия
    public boolean consistent(V variable, Map<V, D> assignment) {
        for (Constraint<V, D> constraint : constraints.get(variable)) {
            if (!constraint.satisfied(assignment)) {
                return false;
            }
        }
        return true;
    }

    public Map<V, D> backtrackingSearch(Map<V, D> assignment) {
        /*
         * По мере поиска значений переменных, удовлетворяющих ограничениям assignment заполняется найденными вариантами присвоений
         */

        // Базовый случай рекурсии: для каждой переменной назначено присваивание
        if (assignment.size() == variables.size()) {
            return assignment;
        }

        // Переменная, которой еще не присвоено значение
        V unassigned = variables.stream()
            .filter(v -> !assignment.containsKey(v))
            .findFirst()
            .get();
        
        // Перебираем значения, доступные для присвоения
        for (D value : domains.get(unassigned)) {
            // локальная копия для дальнейшего поиска
            Map<V, D> localAssignment = new HashMap<>(assignment);
            localAssignment.put(unassigned, value);
            // Продолжение поиска в случае отсутсвия противоречий в назначении переменной
            if (consistent(unassigned, localAssignment)) {
                Map<V, D> result = backtrackingSearch(localAssignment);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    public Map<V, D> backtrackingSearch() {
        return backtrackingSearch(new HashMap<>());
    }
}
