import constraint.MaxLengthConstraint;
import constraint.MinValueConstraint;
import database.Database;
import database.Row;
import database.Table;
import query.EqualFilter;
import query.Filter;
import schema.Column;
import schema.DataType;
import schema.Schema;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Database db = new Database();

        Schema schema = new Schema(List.of(
                new Column("id", DataType.INTEGER, List.of(new MinValueConstraint(1024))),
                new Column("name", DataType.STRING, List.of(new MaxLengthConstraint(20)))
        ));

        db.createTable("employee", schema);

        Table table = db.getTable("employee");
        table.createIndex("id");
        table.createIndex("name");

        Row r1 = new Row();
        r1.put("id", 1025);
        r1.put("name", "Harshit");

        table.insert(r1);

        Row r2 = new Row();
        r2.put("id", 1026);
        r2.put("name", "Rohan");

        table.insert(r2);

        System.out.println("ALL");
        table.printAll();

        List<Filter> filters = List.of(new EqualFilter("id", 1025));
        System.out.println("QUERY");
        System.out.println(table.query(filters));

        table.update(filters, Map.of("name", "Gautam"));
        System.out.println("AFTER UPDATE");
        table.printAll();

        table.delete(filters);
        System.out.println("AFTER DELETE");
        table.printAll();
    }
}