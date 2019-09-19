<?xml version="1.0" encoding="UTF-8"?>
<xmi:XMI xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns="http://repast.sf.net/statecharts" xmlns:notation="http://www.eclipse.org/gmf/runtime/1.0.2/notation">
  <StateMachine xmi:id="_CBrNwc2eEeahM40jKv-Uyw" agentType="moshproject.agents.passenger.Passenger" package="moshproject.agents.passenger.chart" className="CognitiveStatechart" nextID="86" id="CognitiveStatechart" uuid="_CBrNwM2eEeahM40jKv-Uyw">
    <states xmi:type="CompositeState" xmi:id="_EWuZkM2eEeahM40jKv-Uyw" id="Cognitive Processing " uuid="_EXUPcM2eEeahM40jKv-Uyw">
      <children xmi:type="CompositeState" xmi:id="_QXZWIM2eEeahM40jKv-Uyw" id="Individual: Automatic and Reasoning Processing" uuid="_QXZWIc2eEeahM40jKv-Uyw">
        <children xmi:type="State" xmi:id="_fDySEM2eEeahM40jKv-Uyw" id="Repetition" uuid="_fDySEc2eEeahM40jKv-Uyw"/>
        <children xmi:type="State" xmi:id="_i-JO0M2eEeahM40jKv-Uyw" id="Optimising" uuid="_i-JO0c2eEeahM40jKv-Uyw"/>
        <children xmi:type="PseudoState" xmi:id="_oSrDgM2eEeahM40jKv-Uyw" id="Initial State Pointer 8" uuid="_oS00gM2eEeahM40jKv-Uyw"/>
      </children>
      <children xmi:type="PseudoState" xmi:id="_1vCiAM2eEeahM40jKv-Uyw" id="Initial State Pointer 12" uuid="_1vMTAM2eEeahM40jKv-Uyw"/>
      <children xmi:type="CompositeState" xmi:id="_5VS3AM2eEeahM40jKv-Uyw" id="Social Interactions: Automatic and Reasoning Processing" uuid="_5VcoAM2eEeahM40jKv-Uyw">
        <children xmi:type="State" xmi:id="_IKg-0M2fEeahM40jKv-Uyw" id="Imitation" uuid="_IKg-0c2fEeahM40jKv-Uyw"/>
        <children xmi:type="State" xmi:id="_Nz4bEM2fEeahM40jKv-Uyw" id="Inquiring" uuid="_Nz4bEc2fEeahM40jKv-Uyw"/>
        <children xmi:type="PseudoState" xmi:id="_eIAJ0M2fEeahM40jKv-Uyw" id="Initial State Pointer 23" uuid="_eIAJ0c2fEeahM40jKv-Uyw"/>
      </children>
    </states>
    <states xmi:type="PseudoState" xmi:id="_Jd7j8M2eEeahM40jKv-Uyw" id="Entry State Pointer" type="entry"/>
    <transitions xmi:type="Transition" xmi:id="_MTeNsM2eEeahM40jKv-Uyw" from="_Jd7j8M2eEeahM40jKv-Uyw" to="_EWuZkM2eEeahM40jKv-Uyw" id="Transition 1" uuid="_MTeNsc2eEeahM40jKv-Uyw"/>
    <transitions xmi:type="Transition" xmi:id="_qykNYM2eEeahM40jKv-Uyw" from="_oSrDgM2eEeahM40jKv-Uyw" to="_fDySEM2eEeahM40jKv-Uyw" id="Transition 9" uuid="_qykNYc2eEeahM40jKv-Uyw"/>
    <transitions xmi:type="Transition" xmi:id="_2Xcl0M2eEeahM40jKv-Uyw" from="_1vCiAM2eEeahM40jKv-Uyw" to="_QXZWIM2eEeahM40jKv-Uyw" id="Transition 13" uuid="_2XmW0M2eEeahM40jKv-Uyw"/>
    <transitions xmi:type="Transition" xmi:id="_UlYkEM2fEeahM40jKv-Uyw" from="_QXZWIM2eEeahM40jKv-Uyw" to="_5VS3AM2eEeahM40jKv-Uyw" triggerType="condition" triggerConditionCode="agent.getUncertainty()>agent.getUncertaintyTolerance();" messageCheckerClass="Object" id="Transition 19" uuid="_UlYkEc2fEeahM40jKv-Uyw"/>
    <transitions xmi:type="Transition" xmi:id="_ZBxP0M2fEeahM40jKv-Uyw" from="_5VS3AM2eEeahM40jKv-Uyw" to="_QXZWIM2eEeahM40jKv-Uyw" triggerType="condition" triggerConditionCode="agent.getUncertainty()&lt;=agent.getUncertaintyTolerance();" messageCheckerClass="Object" id="Transition 20" uuid="_ZBxP0c2fEeahM40jKv-Uyw"/>
    <transitions xmi:type="Transition" xmi:id="_g2HV4M2fEeahM40jKv-Uyw" from="_eIAJ0M2fEeahM40jKv-Uyw" to="_IKg-0M2fEeahM40jKv-Uyw" id="Transition 24" uuid="_g2HV4c2fEeahM40jKv-Uyw"/>
    <transitions xmi:type="Transition" xmi:id="_jZTEUM2fEeahM40jKv-Uyw" from="_fDySEM2eEeahM40jKv-Uyw" to="_fDySEM2eEeahM40jKv-Uyw" triggerType="condition" triggerConditionCode="agent.getModeSatisfaction()-agent.getPreviousExperience()&lt;=0;&#xA;" messageCheckerClass="Object" id="Transition 27" uuid="_jZc1UM2fEeahM40jKv-Uyw" selfTransition="true"/>
    <transitions xmi:type="Transition" xmi:id="_kpUhwM2fEeahM40jKv-Uyw" from="_IKg-0M2fEeahM40jKv-Uyw" to="_IKg-0M2fEeahM40jKv-Uyw" triggerType="condition" triggerConditionCode="agent.getModeSatisfaction()>=agent.getAspirationLevel();" messageCheckerClass="Object" id="Transition 28" uuid="_kpUhwc2fEeahM40jKv-Uyw" selfTransition="true"/>
    <transitions xmi:type="Transition" xmi:id="_oX0z0M2fEeahM40jKv-Uyw" from="_i-JO0M2eEeahM40jKv-Uyw" to="_fDySEM2eEeahM40jKv-Uyw" triggerType="condition" triggerConditionCode="(!(agent.getModeSatisfaction()&lt;=agent.getAspirationLevel()));" messageCheckerClass="Object" id="Transition 29" uuid="_oX0z0c2fEeahM40jKv-Uyw"/>
    <transitions xmi:type="Transition" xmi:id="_pO0GMM2fEeahM40jKv-Uyw" from="_fDySEM2eEeahM40jKv-Uyw" to="_i-JO0M2eEeahM40jKv-Uyw" triggerType="condition" triggerConditionCode="agent.getModeSatisfaction()&lt;=agent.getAspirationLevel();" messageCheckerClass="Object" id="Transition 30" uuid="_pO0GMc2fEeahM40jKv-Uyw"/>
    <transitions xmi:type="Transition" xmi:id="_sktrAM2fEeahM40jKv-Uyw" from="_IKg-0M2fEeahM40jKv-Uyw" to="_Nz4bEM2fEeahM40jKv-Uyw" triggerType="condition" triggerConditionCode="agent.getModeSatisfaction()&lt;=agent.getAspirationLevel();" messageCheckerClass="Object" id="Transition 33" uuid="_sktrAc2fEeahM40jKv-Uyw"/>
    <transitions xmi:type="Transition" xmi:id="_vkFmMM2fEeahM40jKv-Uyw" from="_Nz4bEM2fEeahM40jKv-Uyw" to="_IKg-0M2fEeahM40jKv-Uyw" triggerType="condition" triggerConditionCode="(!(agent.getModeSatisfaction()&lt;=agent.getAspirationLevel()));" id="Transition 34" uuid="_vkFmMc2fEeahM40jKv-Uyw"/>
  </StateMachine>
  <notation:Diagram xmi:id="_CB0-wM2eEeahM40jKv-Uyw" type="Statechart" element="_CBrNwc2eEeahM40jKv-Uyw" name="statechart3.rsc" measurementUnit="Pixel">
    <children xmi:type="notation:Shape" xmi:id="_EX6sYM2eEeahM40jKv-Uyw" type="2004" element="_EWuZkM2eEeahM40jKv-Uyw" fontName="Segoe UI">
      <children xmi:type="notation:DecorationNode" xmi:id="_EYD2Uc2eEeahM40jKv-Uyw" type="5004"/>
      <children xmi:type="notation:DecorationNode" xmi:id="_EYD2Us2eEeahM40jKv-Uyw" type="7001">
        <children xmi:type="notation:Shape" xmi:id="_QXigEM2eEeahM40jKv-Uyw" type="3002" element="_QXZWIM2eEeahM40jKv-Uyw" fontName="Segoe UI">
          <children xmi:type="notation:DecorationNode" xmi:id="_QXigEs2eEeahM40jKv-Uyw" type="5003"/>
          <children xmi:type="notation:DecorationNode" xmi:id="_QXigE82eEeahM40jKv-Uyw" type="7002">
            <children xmi:type="notation:Shape" xmi:id="_fD8DEM2eEeahM40jKv-Uyw" type="3001" element="_fDySEM2eEeahM40jKv-Uyw" fontName="Segoe UI">
              <children xmi:type="notation:DecorationNode" xmi:id="_fD8DEs2eEeahM40jKv-Uyw" type="5002"/>
              <layoutConstraint xmi:type="notation:Bounds" xmi:id="_fD8DEc2eEeahM40jKv-Uyw" x="24" y="46" width="145" height="61"/>
            </children>
            <children xmi:type="notation:Shape" xmi:id="_i-SYwM2eEeahM40jKv-Uyw" type="3001" element="_i-JO0M2eEeahM40jKv-Uyw" fontName="Segoe UI">
              <children xmi:type="notation:DecorationNode" xmi:id="_i-SYws2eEeahM40jKv-Uyw" type="5002"/>
              <layoutConstraint xmi:type="notation:Bounds" xmi:id="_i-SYwc2eEeahM40jKv-Uyw" x="240" y="46" width="145" height="61"/>
            </children>
            <children xmi:type="notation:Shape" xmi:id="_oS00gc2eEeahM40jKv-Uyw" type="3003" element="_oSrDgM2eEeahM40jKv-Uyw" fontName="Segoe UI">
              <layoutConstraint xmi:type="notation:Bounds" xmi:id="_oS00gs2eEeahM40jKv-Uyw" x="43" y="2"/>
            </children>
          </children>
          <layoutConstraint xmi:type="notation:Bounds" xmi:id="_QXigEc2eEeahM40jKv-Uyw" x="12" y="46" width="421" height="181"/>
        </children>
        <children xmi:type="notation:Shape" xmi:id="_1vMTAc2eEeahM40jKv-Uyw" type="3003" element="_1vCiAM2eEeahM40jKv-Uyw" fontName="Segoe UI">
          <layoutConstraint xmi:type="notation:Bounds" xmi:id="_1vMTAs2eEeahM40jKv-Uyw" x="32" y="-1"/>
        </children>
        <children xmi:type="notation:Shape" xmi:id="_5VcoAc2eEeahM40jKv-Uyw" type="3002" element="_5VS3AM2eEeahM40jKv-Uyw" fontName="Segoe UI">
          <children xmi:type="notation:DecorationNode" xmi:id="_5VcoA82eEeahM40jKv-Uyw" type="5003"/>
          <children xmi:type="notation:DecorationNode" xmi:id="_5VcoBM2eEeahM40jKv-Uyw" type="7002">
            <children xmi:type="notation:Shape" xmi:id="_IKg-0s2fEeahM40jKv-Uyw" type="3001" element="_IKg-0M2fEeahM40jKv-Uyw" fontName="Segoe UI">
              <children xmi:type="notation:DecorationNode" xmi:id="_IKz5wM2fEeahM40jKv-Uyw" type="5002"/>
              <layoutConstraint xmi:type="notation:Bounds" xmi:id="_IKg-082fEeahM40jKv-Uyw" x="24" y="32" width="145" height="61"/>
            </children>
            <children xmi:type="notation:Shape" xmi:id="_Nz4bEs2fEeahM40jKv-Uyw" type="3001" element="_Nz4bEM2fEeahM40jKv-Uyw" fontName="Segoe UI">
              <children xmi:type="notation:DecorationNode" xmi:id="_Nz4bFM2fEeahM40jKv-Uyw" type="5002"/>
              <layoutConstraint xmi:type="notation:Bounds" xmi:id="_Nz4bE82fEeahM40jKv-Uyw" x="240" y="32" width="145" height="61"/>
            </children>
            <children xmi:type="notation:Shape" xmi:id="_eIJTwM2fEeahM40jKv-Uyw" type="3003" element="_eIAJ0M2fEeahM40jKv-Uyw" fontName="Segoe UI">
              <layoutConstraint xmi:type="notation:Bounds" xmi:id="_eIJTwc2fEeahM40jKv-Uyw" x="38" y="-2"/>
            </children>
          </children>
          <layoutConstraint xmi:type="notation:Bounds" xmi:id="_5VcoAs2eEeahM40jKv-Uyw" x="12" y="273" width="421" height="157"/>
        </children>
      </children>
      <layoutConstraint xmi:type="notation:Bounds" xmi:id="_EYD2UM2eEeahM40jKv-Uyw" x="300" y="72" width="469" height="505"/>
    </children>
    <children xmi:type="notation:Shape" xmi:id="_JeEt4M2eEeahM40jKv-Uyw" type="2007" element="_Jd7j8M2eEeahM40jKv-Uyw" fontName="Segoe UI">
      <layoutConstraint xmi:type="notation:Bounds" xmi:id="_JeEt4c2eEeahM40jKv-Uyw" x="327" y="10"/>
    </children>
    <styles xmi:type="notation:DiagramStyle" xmi:id="_CB0-wc2eEeahM40jKv-Uyw"/>
    <edges xmi:type="notation:Edge" xmi:id="_MT65oM2eEeahM40jKv-Uyw" type="4001" element="_MTeNsM2eEeahM40jKv-Uyw" source="_JeEt4M2eEeahM40jKv-Uyw" target="_EX6sYM2eEeahM40jKv-Uyw">
      <styles xmi:type="notation:RoutingStyle" xmi:id="_MT65oc2eEeahM40jKv-Uyw"/>
      <styles xmi:type="notation:FontStyle" xmi:id="_MT65os2eEeahM40jKv-Uyw" fontName="Segoe UI"/>
      <bendpoints xmi:type="notation:RelativeBendpoints" xmi:id="_MT65o82eEeahM40jKv-Uyw" points="[0, 0, 3, -40]$[-3, 40, 0, 0]"/>
      <sourceAnchor xmi:type="notation:IdentityAnchor" xmi:id="_MUEqoM2eEeahM40jKv-Uyw" id="CENTER"/>
      <targetAnchor xmi:type="notation:IdentityAnchor" xmi:id="_MUEqoc2eEeahM40jKv-Uyw" id="(0.07249466950959488,0.0)"/>
    </edges>
    <edges xmi:type="notation:Edge" xmi:id="_qytXUM2eEeahM40jKv-Uyw" type="4001" element="_qykNYM2eEeahM40jKv-Uyw" source="_oS00gc2eEeahM40jKv-Uyw" target="_fD8DEM2eEeahM40jKv-Uyw">
      <styles xmi:type="notation:RoutingStyle" xmi:id="_qytXUc2eEeahM40jKv-Uyw"/>
      <styles xmi:type="notation:FontStyle" xmi:id="_qytXUs2eEeahM40jKv-Uyw" fontName="Segoe UI"/>
      <bendpoints xmi:type="notation:RelativeBendpoints" xmi:id="_qytXU82eEeahM40jKv-Uyw" points="[0, 0, -46, -73]$[31, 49, -15, -24]"/>
      <sourceAnchor xmi:type="notation:IdentityAnchor" xmi:id="_qytXVM2eEeahM40jKv-Uyw" id="CENTER"/>
      <targetAnchor xmi:type="notation:IdentityAnchor" xmi:id="_sWdpEM2eEeahM40jKv-Uyw" id="(0.21379310344827587,0.09836065573770492)"/>
    </edges>
    <edges xmi:type="notation:Edge" xmi:id="_2XmW0c2eEeahM40jKv-Uyw" type="4001" element="_2Xcl0M2eEeahM40jKv-Uyw" source="_1vMTAc2eEeahM40jKv-Uyw" target="_QXigEM2eEeahM40jKv-Uyw">
      <styles xmi:type="notation:RoutingStyle" xmi:id="_2XmW0s2eEeahM40jKv-Uyw"/>
      <styles xmi:type="notation:FontStyle" xmi:id="_2XmW082eEeahM40jKv-Uyw" fontName="Segoe UI"/>
      <bendpoints xmi:type="notation:RelativeBendpoints" xmi:id="_2XmW1M2eEeahM40jKv-Uyw" points="[0, 0, -3, -46]$[-10, 40, -13, -6]"/>
      <sourceAnchor xmi:type="notation:IdentityAnchor" xmi:id="_2XmW1c2eEeahM40jKv-Uyw" id="CENTER"/>
      <targetAnchor xmi:type="notation:IdentityAnchor" xmi:id="_2XmW1s2eEeahM40jKv-Uyw" id="(0.07125890736342043,0.03314917127071823)"/>
    </edges>
    <edges xmi:type="notation:Edge" xmi:id="_UlYkEs2fEeahM40jKv-Uyw" type="4001" element="_UlYkEM2fEeahM40jKv-Uyw" source="_QXigEM2eEeahM40jKv-Uyw" target="_5VcoAc2eEeahM40jKv-Uyw">
      <styles xmi:type="notation:RoutingStyle" xmi:id="_UlYkE82fEeahM40jKv-Uyw"/>
      <styles xmi:type="notation:FontStyle" xmi:id="_UlYkFM2fEeahM40jKv-Uyw" fontName="Segoe UI"/>
      <bendpoints xmi:type="notation:RelativeBendpoints" xmi:id="_UlYkFc2fEeahM40jKv-Uyw" points="[3, 7, 0, -89]$[-13, 89, -16, -7]"/>
      <sourceAnchor xmi:type="notation:IdentityAnchor" xmi:id="_UliVEM2fEeahM40jKv-Uyw" id="(0.12114014251781473,0.9723756906077348)"/>
      <targetAnchor xmi:type="notation:IdentityAnchor" xmi:id="_UliVEc2fEeahM40jKv-Uyw" id="(0.12114014251781473,0.044585987261146494)"/>
    </edges>
    <edges xmi:type="notation:Edge" xmi:id="_ZBxP0s2fEeahM40jKv-Uyw" type="4001" element="_ZBxP0M2fEeahM40jKv-Uyw" source="_5VcoAc2eEeahM40jKv-Uyw" target="_QXigEM2eEeahM40jKv-Uyw">
      <styles xmi:type="notation:RoutingStyle" xmi:id="_ZBxP082fEeahM40jKv-Uyw"/>
      <styles xmi:type="notation:FontStyle" xmi:id="_ZBxP1M2fEeahM40jKv-Uyw" fontName="Segoe UI"/>
      <bendpoints xmi:type="notation:RelativeBendpoints" xmi:id="_ZBxP1c2fEeahM40jKv-Uyw" points="[-3, -20, 0, 53]$[5, -66, 8, 7]"/>
      <sourceAnchor xmi:type="notation:IdentityAnchor" xmi:id="_ZBxP1s2fEeahM40jKv-Uyw" id="(0.7600950118764845,0.12738853503184713)"/>
      <targetAnchor xmi:type="notation:IdentityAnchor" xmi:id="_ZBxP182fEeahM40jKv-Uyw" id="(0.7529691211401425,0.9613259668508287)"/>
    </edges>
    <edges xmi:type="notation:Edge" xmi:id="_g2HV4s2fEeahM40jKv-Uyw" type="4001" element="_g2HV4M2fEeahM40jKv-Uyw" source="_eIJTwM2fEeahM40jKv-Uyw" target="_IKg-0s2fEeahM40jKv-Uyw">
      <styles xmi:type="notation:RoutingStyle" xmi:id="_g2HV482fEeahM40jKv-Uyw"/>
      <styles xmi:type="notation:FontStyle" xmi:id="_g2HV5M2fEeahM40jKv-Uyw" fontName="Segoe UI"/>
      <bendpoints xmi:type="notation:RelativeBendpoints" xmi:id="_g2HV5c2fEeahM40jKv-Uyw" points="[0, 0, 0, -28]$[-2, 27, -2, -1]"/>
      <sourceAnchor xmi:type="notation:IdentityAnchor" xmi:id="_g2Qf0M2fEeahM40jKv-Uyw" id="CENTER"/>
      <targetAnchor xmi:type="notation:IdentityAnchor" xmi:id="_g2Qf0c2fEeahM40jKv-Uyw" id="(0.14482758620689656,0.01639344262295082)"/>
    </edges>
    <edges xmi:type="notation:Edge" xmi:id="_jZc1Uc2fEeahM40jKv-Uyw" type="4001" element="_jZTEUM2fEeahM40jKv-Uyw" source="_fD8DEM2eEeahM40jKv-Uyw" target="_fD8DEM2eEeahM40jKv-Uyw">
      <styles xmi:type="notation:RoutingStyle" xmi:id="_jZc1Us2fEeahM40jKv-Uyw"/>
      <styles xmi:type="notation:FontStyle" xmi:id="_jZc1U82fEeahM40jKv-Uyw" fontName="Segoe UI"/>
      <bendpoints xmi:type="notation:RelativeBendpoints" xmi:id="_jZc1VM2fEeahM40jKv-Uyw" points="[-47, 51, -15, 16]$[4, 49, 36, 14]"/>
      <sourceAnchor xmi:type="notation:IdentityAnchor" xmi:id="_jZl_QM2fEeahM40jKv-Uyw" id="(0.9724137931034482,0.16393442622950818)"/>
      <targetAnchor xmi:type="notation:IdentityAnchor" xmi:id="_jZl_Qc2fEeahM40jKv-Uyw" id="(0.7517241379310344,0.7377049180327869)"/>
    </edges>
    <edges xmi:type="notation:Edge" xmi:id="_kpUhws2fEeahM40jKv-Uyw" type="4001" element="_kpUhwM2fEeahM40jKv-Uyw" source="_IKg-0s2fEeahM40jKv-Uyw" target="_IKg-0s2fEeahM40jKv-Uyw">
      <styles xmi:type="notation:RoutingStyle" xmi:id="_kpUhw82fEeahM40jKv-Uyw"/>
      <styles xmi:type="notation:FontStyle" xmi:id="_kpUhxM2fEeahM40jKv-Uyw" fontName="Segoe UI"/>
      <bendpoints xmi:type="notation:RelativeBendpoints" xmi:id="_kpUhxc2fEeahM40jKv-Uyw" points="[2, -2, 19, -18]$[2, 18, 19, 2]"/>
      <sourceAnchor xmi:type="notation:IdentityAnchor" xmi:id="_kpeSwM2fEeahM40jKv-Uyw" id="(0.9862068965517241,0.3442622950819672)"/>
      <targetAnchor xmi:type="notation:IdentityAnchor" xmi:id="_kpeSwc2fEeahM40jKv-Uyw" id="(0.8689655172413793,0.6065573770491803)"/>
    </edges>
    <edges xmi:type="notation:Edge" xmi:id="_oX0z0s2fEeahM40jKv-Uyw" type="4001" element="_oX0z0M2fEeahM40jKv-Uyw" source="_i-SYwM2eEeahM40jKv-Uyw" target="_fD8DEM2eEeahM40jKv-Uyw">
      <styles xmi:type="notation:RoutingStyle" xmi:id="_oX0z082fEeahM40jKv-Uyw"/>
      <styles xmi:type="notation:FontStyle" xmi:id="_oX0z1M2fEeahM40jKv-Uyw" fontName="Segoe UI"/>
      <bendpoints xmi:type="notation:RelativeBendpoints" xmi:id="_oX0z1c2fEeahM40jKv-Uyw" points="[-5, 3, 74, 0]$[-76, 2, 3, -1]"/>
      <sourceAnchor xmi:type="notation:IdentityAnchor" xmi:id="_oX-k0M2fEeahM40jKv-Uyw" id="(0.034482758620689655,0.3114754098360656)"/>
      <targetAnchor xmi:type="notation:IdentityAnchor" xmi:id="_oX-k0c2fEeahM40jKv-Uyw" id="(0.9793103448275862,0.36065573770491804)"/>
    </edges>
    <edges xmi:type="notation:Edge" xmi:id="_pO0GMs2fEeahM40jKv-Uyw" type="4001" element="_pO0GMM2fEeahM40jKv-Uyw" source="_fD8DEM2eEeahM40jKv-Uyw" target="_i-SYwM2eEeahM40jKv-Uyw">
      <styles xmi:type="notation:RoutingStyle" xmi:id="_pO0GM82fEeahM40jKv-Uyw"/>
      <styles xmi:type="notation:FontStyle" xmi:id="_pO0GNM2fEeahM40jKv-Uyw" fontName="Segoe UI"/>
      <bendpoints xmi:type="notation:RelativeBendpoints" xmi:id="_pO0GNc2fEeahM40jKv-Uyw" points="[5, 3, -72, 0]$[76, 3, -1, 0]"/>
      <sourceAnchor xmi:type="notation:IdentityAnchor" xmi:id="_pO0GNs2fEeahM40jKv-Uyw" id="(0.9655172413793104,0.6885245901639344)"/>
      <targetAnchor xmi:type="notation:IdentityAnchor" xmi:id="_pO0GN82fEeahM40jKv-Uyw" id="(0.006896551724137931,0.7377049180327869)"/>
    </edges>
    <edges xmi:type="notation:Edge" xmi:id="_sk208M2fEeahM40jKv-Uyw" type="4001" element="_sktrAM2fEeahM40jKv-Uyw" source="_IKg-0s2fEeahM40jKv-Uyw" target="_Nz4bEs2fEeahM40jKv-Uyw">
      <styles xmi:type="notation:RoutingStyle" xmi:id="_sk208c2fEeahM40jKv-Uyw"/>
      <styles xmi:type="notation:FontStyle" xmi:id="_sk208s2fEeahM40jKv-Uyw" fontName="Segoe UI"/>
      <bendpoints xmi:type="notation:RelativeBendpoints" xmi:id="_sk20882fEeahM40jKv-Uyw" points="[8, 3, -78, 0]$[79, 1, -7, -2]"/>
      <sourceAnchor xmi:type="notation:IdentityAnchor" xmi:id="_slAl8M2fEeahM40jKv-Uyw" id="(0.9448275862068966,0.14754098360655737)"/>
      <targetAnchor xmi:type="notation:IdentityAnchor" xmi:id="_slAl8c2fEeahM40jKv-Uyw" id="(0.04827586206896552,0.19672131147540983)"/>
    </edges>
    <edges xmi:type="notation:Edge" xmi:id="_vkFmMs2fEeahM40jKv-Uyw" type="4001" element="_vkFmMM2fEeahM40jKv-Uyw" source="_Nz4bEs2fEeahM40jKv-Uyw" target="_IKg-0s2fEeahM40jKv-Uyw">
      <styles xmi:type="notation:RoutingStyle" xmi:id="_vkFmM82fEeahM40jKv-Uyw"/>
      <styles xmi:type="notation:FontStyle" xmi:id="_vkFmNM2fEeahM40jKv-Uyw" fontName="Segoe UI"/>
      <bendpoints xmi:type="notation:RelativeBendpoints" xmi:id="_vkFmNc2fEeahM40jKv-Uyw" points="[0, 2, 73, 0]$[-71, 2, 2, 0]"/>
      <sourceAnchor xmi:type="notation:IdentityAnchor" xmi:id="_vkFmNs2fEeahM40jKv-Uyw" id="(0.0,0.7540983606557377)"/>
      <targetAnchor xmi:type="notation:IdentityAnchor" xmi:id="_vkFmN82fEeahM40jKv-Uyw" id="(0.9862068965517241,0.7868852459016393)"/>
    </edges>
  </notation:Diagram>
</xmi:XMI>
