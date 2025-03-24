package crud.shared.persistence.datasource;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import com.google.inject.Singleton;

@Singleton
public class FakeCSVConnectionManager implements ICSVConnectionManager {
  private final Map<String, String> fakeDataByTableName = new HashMap<>();

  private static final String  FAKE_USERS_DATA = """
    id,username,password
    1,johndoe,secret123
    2,janedoe,password456
    """;

  private static final String FAKE_PRODUCTS_DATA = """
    id,name,short_description,price,category,long_description,quantity
    1,Galaxy Explorer,Explore the stars with ease,499.99,Electronics,High-tech telescope with advanced features,15
    2,Mountain Trekker,Conquer the toughest trails,129.99,Sports,Durable hiking boots for all terrains,50
    3,Ocean Breeze,Relax by the sea,19.99,Home,Fragrant candle with a calming ocean scent,200
    4,Quantum Laptop,Unleash your productivity,999.99,Computers,High-performance laptop with cutting-edge specs,30
    5,Chef's Delight,Master the art of cooking,59.99,Kitchen,Premium knife set for professional chefs,100
    6,Adventure Backpack,Carry your world,79.99,Travel,Spacious and durable backpack for adventurers,75
    7,SmartFit Watch,Track your fitness goals,149.99,Health,Advanced smartwatch with health monitoring features,40
    8,Artisan Coffee,Awaken your senses,14.99,Food,Gourmet coffee beans sourced from the finest farms,300
    9,Virtual Reality Kit,Step into another world,399.99,Gaming,Immersive VR headset with motion controllers,25
    10,Electric Scooter,Commute with style,499.99,Transportation,Lightweight and eco-friendly electric scooter,20
    """;

  public FakeCSVConnectionManager() {
    this.fakeDataByTableName.put("users", FakeCSVConnectionManager.FAKE_USERS_DATA);
    this.fakeDataByTableName.put("sessions", "sessions.csv");
    this.fakeDataByTableName.put("products", FakeCSVConnectionManager.FAKE_PRODUCTS_DATA);
  }

  @Override
  public <T> T executeWithReader(String tableName, IReaderAction<T> action) throws Exception {

    try(Reader reader = new StringReader(fakeDataByTableName.get(tableName))){
      return action.execute(reader);
    }
  }

  @Override
  public <T> T executeWithWriter(String tableName, IWriterAction<T> action) throws Exception {
    return null;
  }

  @Override
  public void registerPath(String identifier, String path) {
    throw new UnsupportedOperationException("Not implemented");
  }

}
