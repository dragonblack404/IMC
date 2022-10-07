package dad.imc;


	import javafx.application.Application;
	import javafx.beans.binding.DoubleExpression;
	import javafx.beans.property.DoubleProperty;
	import javafx.beans.property.SimpleDoubleProperty;
	import javafx.beans.property.SimpleStringProperty;
	import javafx.beans.property.StringProperty;
	import javafx.geometry.Pos;
	import javafx.scene.Scene;
	import javafx.scene.control.Label;
	import javafx.scene.control.TextField;
	import javafx.scene.layout.HBox;
	import javafx.scene.layout.VBox;
	import javafx.stage.Stage;
	import javafx.util.converter.NumberStringConverter;

	public class ImcApp extends Application {


		
		private TextField pesoText;
		private TextField alturaText;
		private Label imcLabel, estadoLabel;
		
		
		private DoubleProperty peso = new SimpleDoubleProperty();
		private DoubleProperty altura = new SimpleDoubleProperty();
		private DoubleProperty imc = new SimpleDoubleProperty();
		private StringProperty estado = new SimpleStringProperty();

		@Override
		public void start(Stage primaryStage) throws Exception {

			pesoText = new TextField();
			pesoText.setPrefWidth(80);
			
			alturaText = new TextField();
			alturaText.setPrefWidth(80);
			imcLabel = new Label();
			estadoLabel = new Label();
			
			HBox pesoPanel = new HBox();
			pesoPanel.setSpacing(5);
			pesoPanel.setFillHeight(false);
			pesoPanel.setAlignment(Pos.CENTER);
			pesoPanel.getChildren().addAll(new Label("Peso:"), pesoText, new Label("kg"));

			HBox alturaPanel = new HBox();
			alturaPanel.setSpacing(5);
			alturaPanel.setFillHeight(false);
			alturaPanel.setAlignment(Pos.CENTER);
			alturaPanel.getChildren().addAll(new Label("Altura:"), alturaText, new Label("cm"));

			HBox imcPanel = new HBox();
			imcPanel.setSpacing(5);
			imcPanel.setFillHeight(false);
			imcPanel.setAlignment(Pos.CENTER);
			imcPanel.getChildren().addAll(new Label("IMC:"), imcLabel);

			VBox rootPanel = new VBox();
			rootPanel.setSpacing(5);
			rootPanel.setFillWidth(false); 
			rootPanel.setAlignment(Pos.CENTER); 
			rootPanel.getChildren().addAll(pesoPanel, alturaPanel, imcPanel, estadoLabel);

			Scene scene = new Scene(rootPanel, 480, 200);
			
			primaryStage.setTitle("IMC (David Alejandro Hernández Alonso 2º DAM-A)");
			primaryStage.setScene(scene);
			primaryStage.show();

			
			pesoText.textProperty().bindBidirectional(peso, new NumberStringConverter());
			alturaText.textProperty().bindBidirectional(altura, new NumberStringConverter());
			imcLabel.textProperty().bindBidirectional(imc, new NumberStringConverter());
			estadoLabel.textProperty().bind(estado);
			
			DoubleExpression alturaM = altura.divide(100);
			imc.bind(peso.divide(alturaM.multiply(alturaM)));

			imc.addListener((o, ov, nv) -> {
				String estado_imc = "";
				double newValue = nv.doubleValue();
				if (newValue < 18.5) {
					estado_imc = "Bajo peso";
				} else if (newValue >= 18.5 && newValue < 25) {
					estado_imc = "Peso normal";
				} else if (newValue >= 25 && newValue < 30) {
					estado_imc = "Sobrepeso";
				} else {
					estado_imc = "Obeso";
				}
				estado.set(estado_imc);
			});

		}

		public static void main(String[] args) {
			launch(args);
		}
		
	}
	

