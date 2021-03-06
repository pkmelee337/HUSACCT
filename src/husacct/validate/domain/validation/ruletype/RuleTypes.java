package husacct.validate.domain.validation.ruletype;

import husacct.validate.domain.validation.DefaultSeverities;

import java.util.EnumSet;

public enum RuleTypes {
	IS_NOT_ALLOWED("IsNotAllowedToUse", DefaultSeverities.HIGH),
	IS_NOT_ALLOWED_BACK_CALL("IsNotAllowedToMakeBackCall", DefaultSeverities.HIGH),
	IS_NOT_ALLOWED_SKIP_CALL("IsNotAllowedToMakeSkipCall", DefaultSeverities.LOW),
	IS_ALLOWED("IsAllowedToUse", DefaultSeverities.LOW),
	IS_ONLY_ALLOWED("IsOnlyAllowedToUse", DefaultSeverities.LOW),
	IS_ONLY_MODULE_ALLOWED("IsOnlyModuleAllowedToUse", DefaultSeverities.MEDIUM),
	MUST_USE("MustUse", DefaultSeverities.MEDIUM),
	NAMING_CONVENTION("NamingConvention", DefaultSeverities.MEDIUM),
	NAMING_CONVENTION_EXCEPTION("NamingConventionException", DefaultSeverities.MEDIUM),
	VISIBILITY_CONVENTION("VisibilityConvention", DefaultSeverities.MEDIUM),
	VISIBILITY_CONVENTION_EXCEPTION("VisibilityConventionException", DefaultSeverities.MEDIUM),
	INTERFACE_CONVENTION("InterfaceConvention", DefaultSeverities.LOW),
	SUBCLASS_CONVENTION("SubClassConvention", DefaultSeverities.MEDIUM);

	public static final EnumSet<RuleTypes> mainRuleTypes = 
			EnumSet.of(SUBCLASS_CONVENTION ,
			INTERFACE_CONVENTION ,
			IS_NOT_ALLOWED, 
			IS_ONLY_ALLOWED, 
			IS_ONLY_MODULE_ALLOWED, 
			MUST_USE, 
			IS_NOT_ALLOWED_BACK_CALL, 
			IS_NOT_ALLOWED_SKIP_CALL, 
			NAMING_CONVENTION, 
			VISIBILITY_CONVENTION);

	private final String key;
	private final DefaultSeverities defaultSeverity;

	RuleTypes(String key, DefaultSeverities defaultSeverity){
		this.key = key;
		this.defaultSeverity = defaultSeverity;
	}

	public DefaultSeverities getDefaultSeverity() {
		return defaultSeverity;
	}

	@Override
	public String toString(){
		return key;
	}
}