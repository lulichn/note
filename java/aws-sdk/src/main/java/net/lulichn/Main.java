package net.lulichn;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;

public class Main {
  private final AmazonDynamoDB dynamoDB;
  public static void main(String[] args) throws Exception {
    var instance = new Main();
    instance.listTables();
    instance.putItem();
    instance.getItem();;
  }

  private Main() {
    this.dynamoDB = AmazonDynamoDBClientBuilder.defaultClient();
  }

  private void listTables() {
    var tables = this.dynamoDB.listTables().getTableNames();
    for (var table: tables) {
      System.out.println(table);
    }
  }

  private void putItem() {
    var inner = new Inner("A", "B");
    var item = new Sample("foo", List.of(inner));

    var mapper = new DynamoDBMapper(this.dynamoDB);
    mapper.save(item);
  }

  private void getItem() {
    var item = new Sample("foo");

    var mapper = new DynamoDBMapper(this.dynamoDB);
    var loaded = mapper.load(item);
    System.out.println(loaded);
  }

  @DynamoDBTable(tableName = "sample")
  public static class Sample {
    private String id;
    private List<Inner> map;


    public Sample() {
    }

    public Sample(String id) {
      this.id = id;
    }

    public Sample(String id, List<Inner> map) {
      this.id = id;
      this.map = map;
    }

    @DynamoDBHashKey
    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public List<Inner> getMap() {
      return map;
    }

    public void setMap(List<Inner> map) {
      this.map = map;
    }
  }

  @DynamoDBDocument
  public static class Inner {
    private String a;
    private String b;

    public Inner() {
    }

    public Inner(String a, String b) {
      this.a = a;
      this.b = b;
    }

    public String getA() {
      return a;
    }

    public void setA(String a) {
      this.a = a;
    }

    public String getB() {
      return b;
    }

    public void setB(String b) {
      this.b = b;
    }
  }
}
