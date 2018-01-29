package com.longshen.msgtw.route.fitler;

import com.longshen.msgtw.base.connector.IConnector;
import com.longshen.msgtw.exception.GtwException;
import com.longshen.msgtw.filter.IFilter;
import com.longshen.msgtw.filter.annotation.FilterType;

public abstract class AbstractConnectorFilter<REQ, RES> implements IFilter<REQ, RES> {

	IConnector<REQ, RES> connector=null;
	@Override
	public void init() throws GtwException {
       //do nothing
	}

	@Override
	public void refresh() throws GtwException {
	      //do nothing
	}

	@SuppressWarnings("unchecked")
	@Override
	public <MOD> void mod(MOD mod) throws GtwException {
       if(mod instanceof IConnector ) {
    	    connector = (IConnector<REQ, RES>) mod;
       }
	}

	@Override
	public boolean check(FilterType filterType, REQ req, RES res, Object... args) throws GtwException {
		return false;
	}

	@Override
	public RES run(FilterType filterType, REQ req, RES res, Object... args) throws GtwException {
        try {
			res=connector.connector(req, args);
		} catch (Exception e) {
			return doException(req, res, e, args);
		}
        return res;
	}
	
	protected abstract RES doException(REQ req,RES res,Exception ex,Object...args) throws GtwException;

}
