import com.mob.interview.atmservice.model.*
import com.mob.interview.atmservice.model.enums.AtmType
import com.mob.interview.atmservice.service.AtmService
import com.mob.interview.atmservice.service.impl.AtmServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class AtmServiceTest extends Specification {

    final restTemplate = Mock(RestTemplate)
    AtmService atmservice

    def setup() {
        atmservice = new AtmServiceImpl(restTemplate);
    }

    def "get All Atm" () {
        given:
        String filter = null;
        String json = "[{\n" +
                "\t\"address\": {\n" +
                "\t\t\"street\": \"de lindeboom\",\n" +
                "\t\t\"housenumber\": \"16\",\n" +
                "\t\t\"postalcode\": \"3641 EX\",\n" +
                "\t\t\"city\": \"Mijdrecht\",\n" +
                "\t\t\"geoLocation\": {\n" +
                "\t\t\t\"lat\": \"52.208429\",\n" +
                "\t\t\t\"lng\": \"4.866982\"\n" +
                "\t\t}\n" +
                "\t},\n" +
                "\t\"distance\": 0,\n" +
                "\t\"openingHours\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"dayOfWeek\": 2,\n" +
                "\t\t\t\"hours\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"hourFrom\": \"09:30\",\n" +
                "\t\t\t\t\t\"hourTo\": \"17:30\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t}\t\t\n" +
                "\t],\n" +
                "\t\"functionality\": \"Geld storten en opnemen\",\n" +
                "\t\"type\": \"GELDMAAT\"\n" +
                "}]"

        def geoLocation = new GeoLocation(lat:"52.208429", lon: "4.866982")
        def address = new Address(street:"", housenumber: "", postalcode: "", city: "", geoLocation: geoLocation)
        def openingHours = new OpeningHours(dayOfWeek:2, hours:[new Hours(hourFrom: "09:30", hourTo: "17:30")])
        def atm = new Atm(address: address, openingHours: [openingHours], distance:0, functionality: "Geld storten en opnemen", atmType:AtmType.GELDMAAT)
        def atmsList = [atm]
        when:
        atmservice.getAllAtms(filter)
        then:
        1 * restTemplate.exchange(_, _, _, _) >> ResponseEntity.ok(json)
        atmsList.size() == 1

    }

    def "get All Atms by city name" () {
        given:
        String filter = "Mijdrecht";
        String json = "[{\n" +
                "\t\"address\": {\n" +
                "\t\t\"street\": \"de lindeboom\",\n" +
                "\t\t\"housenumber\": \"16\",\n" +
                "\t\t\"postalcode\": \"3641 EX\",\n" +
                "\t\t\"city\": \"Mijdrecht\",\n" +
                "\t\t\"geoLocation\": {\n" +
                "\t\t\t\"lat\": \"52.208429\",\n" +
                "\t\t\t\"lng\": \"4.866982\"\n" +
                "\t\t}\n" +
                "\t},\n" +
                "\t\"distance\": 0,\n" +
                "\t\"openingHours\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"dayOfWeek\": 2,\n" +
                "\t\t\t\"hours\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"hourFrom\": \"09:30\",\n" +
                "\t\t\t\t\t\"hourTo\": \"17:30\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t}\t\t\n" +
                "\t],\n" +
                "\t\"functionality\": \"Geld storten en opnemen\",\n" +
                "\t\"type\": \"GELDMAAT\"\n" +
                "}]"

        def geoLocation = new GeoLocation(lat:"52.208429", lon: "4.866982")
        def address = new Address(street:"", housenumber: "", postalcode: "", city: "", geoLocation: geoLocation)
        def openingHours = new OpeningHours(dayOfWeek:2, hours:[new Hours(hourFrom: "09:30", hourTo: "17:30")])
        def atm = new Atm(address: address, openingHours: [openingHours], distance:0, functionality: "Geld storten en opnemen", atmType:AtmType.GELDMAAT)
        def atmsList = [atm]
        when:
        atmservice.getAllAtms(filter)
        then:
        1 * restTemplate.exchange(_, _, _, _) >> ResponseEntity.ok(json)
        atmsList.size() == 1

    }

    def "get All Atms by city name, checking with city name which is not present in the list" () {
        given:
        String filter = "Mijdrec";
        String json = "[{\n" +
                "\t\"address\": {\n" +
                "\t\t\"street\": \"de lindeboom\",\n" +
                "\t\t\"housenumber\": \"16\",\n" +
                "\t\t\"postalcode\": \"3641 EX\",\n" +
                "\t\t\"city\": \"Mijdrecht\",\n" +
                "\t\t\"geoLocation\": {\n" +
                "\t\t\t\"lat\": \"52.208429\",\n" +
                "\t\t\t\"lng\": \"4.866982\"\n" +
                "\t\t}\n" +
                "\t},\n" +
                "\t\"distance\": 0,\n" +
                "\t\"openingHours\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"dayOfWeek\": 2,\n" +
                "\t\t\t\"hours\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"hourFrom\": \"09:30\",\n" +
                "\t\t\t\t\t\"hourTo\": \"17:30\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t}\t\t\n" +
                "\t],\n" +
                "\t\"functionality\": \"Geld storten en opnemen\",\n" +
                "\t\"type\": \"GELDMAAT\"\n" +
                "}]"

        def geoLocation = new GeoLocation(lat:"52.208429", lon: "4.866982")
        def address = new Address(street:"", housenumber: "", postalcode: "", city: "", geoLocation: geoLocation)
        def openingHours = new OpeningHours(dayOfWeek:2, hours:[new Hours(hourFrom: "09:30", hourTo: "17:30")])
        def atm = new Atm(address: address, openingHours: [openingHours], distance:0, functionality: "Geld storten en opnemen", atmType:AtmType.GELDMAAT)
        def atmsList = []
        when:
        atmservice.getAllAtms(filter)
        then:
        1 * restTemplate.exchange(_, _, _, _) >> ResponseEntity.ok(json)
        atmsList.size() == 0

    }


    def "get All Atms from incorrect json data" () {
        given:
        String filter = null;
        String json = ")]}',\n" +
                "[{\"address\":{\"street\":\"Pieter Calandlaan\",\"housenumber\":\"212A\",\"postalcode\":\"1069 LA\",\"city\":\"Amsterdam\",\"geoLocation\":{\"lat\":\"52.352156\",\"lng\":\"4.79933\"}},\"distance\":0,\"openingHours\":[{\"dayOfWeek\":2,\"hours\":[{\"hourFrom\":\"08:00\",\"hourTo\":\"22:00\"}]}],\"functionality\":\"Geldautomaat\",\"type\":\"GELDMAAT\"}]"

        def geoLocation = new GeoLocation(lat:"52.208429", lon: "4.866982")
        def address = new Address(street:"", housenumber: "", postalcode: "", city: "", geoLocation: geoLocation)
        def openingHours = new OpeningHours(dayOfWeek:2, hours:[new Hours(hourFrom: "09:30", hourTo: "17:30")])
        def atm = new Atm(address: address, openingHours: [openingHours], distance:0, functionality: "Geld storten en opnemen", atmType:AtmType.GELDMAAT)
        def atmsList = [atm]
        when:
        atmservice.getAllAtms(filter)
        then:
        1 * restTemplate.exchange(_, _, _, _) >> ResponseEntity.ok(json)
        atmsList.size() == 1

    }
}
