package selectors;

public class Selectors {

    private String sel;
    private String outSQL;
    private String selClauses;

//    static {
//        Class<Selectors> SELECTORS = Selectors.class;
//    }
    
    public Selectors() {
        System.out.println("default constructor");
        selClauses = "";
    }

    public Selectors(String selector) {
        selClauses = "";
        sel = selector;
    }

    public static Selectors $() {
        return new Selectors();
    }

    @Override
    public String toString() {
        return outSQL;
    }

    public static Selectors $(String selector) {
        return new Selectors(selector);
    }

    public Selectors selectAll() {
        outSQL = "SELECT * FROM " + this.sel.toUpperCase() + ";";
        return this;
    }

    public Selectors select(String selClause) {
        String[] selectedClause = selClause.split(",");

        for (String sClause : selectedClause) {
            if (selClauses.length() > 0) {
                selClauses += ", " + sClause.toUpperCase().concat(",");
                selClauses = selClauses.substring(0, selClauses.length() - 1);
            } else {
                selClauses += sClause.toUpperCase();
            }
        }

        outSQL = "SELECT " + selClauses + " FROM " + this.sel.toUpperCase() + ";";
        return this;
    }

    public static void main(String[] args) {
        System.out.println("selectAll:");
        System.out.println($("myTable1").selectAll());

        System.out.println("\nselect(\"*\") :");
        System.out.println($("myTable1").select("*"));

        System.out.println("\nAuto toString()");
        System.out.println($("myTable1").select("*"));

        Selectors s = $("myTable").select("*,lastName").select("firstName");
        String out = s.toString();
        System.out.println("\nreturn selectorObj, toString() and multi-select :");
        System.out.println(out);

    }
}
