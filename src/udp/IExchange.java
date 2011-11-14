package udp;

import java.io.Serializable;

import common.BoxOfficePOA;

public interface IExchange extends Serializable {

	public void execute(BoxOfficePOA boxOffice);
}
