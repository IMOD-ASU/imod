<%@ page import="imodv6.PedagogyMode" %>
<%@ page import="imodv6.PedagogyActivityFocus" %>
<%@ page import="imodv6.PedagogyActivityDuration" %>
<%@ page import="imodv6.PedagogyActivity" %>
<%@ page import="imodv6.PedagogyReference" %>

<table id="topicScheduleMainTable" style="width: 100%;" cellpadding="10px">
	<tbody id="showTopicScheduleBody">
		<tr>
			<td style="font-size:13px;font-weight:bold;" width="20%">
				Title
				<span style="color:red">
					*
				</span>
			</td>
			<td>
				<g:field type="text" name="pedagogyTitle" id="pedagogyTitle_dialog" value="${pedagogyTech?.pedagogyTitle}" style="font-size:14px;" size="49"/>
			</td>
		</tr>
		<tr>
			<td style="font-size:13px;font-weight:bold;" width="20%">
				Description of Strategy
			</td>
			<td>
				<g:textArea name="pedagogyDescription" rows="5" cols="42">
					${pedagogyTech?.pedagogyDescription}
				</g:textArea>
			</td>
		</tr>
		<tr>
			<td style="font-size:13px;font-weight:bold;" width="20%">
				Mode
				<span style="color:red">
					*
				</span>
			</td>
			<td>
				<g:select style="font-size:13px;font-weight:bold;" name="pedagogyModeId" id="pedagogyModeId_dialog" from="${PedagogyMode.list()}" optionKey="id" optionValue="name" noSelection="['':'Select one']" value="${pedagogyTech?.pedagogyMode?.id}"/>
			</td>
		</tr>
		<tr>
			<td colspan="100%">
				<table class="pedTechTable" border="1" style="width:100%">
					<tr>
						<td colspan="100%">
							<g:if test="${pedagogyTech}">
								<i style="font-size: 11px;font-weight:bold;">
									<font color="#F7931D">
										To make multiple selections on "Focus", press 'Ctrl' while choosing options.
										<br/>
										Domain, Domain Category and Knowledge Dimension cannot be changed
									</font>
								</i>
							</g:if>
							<g:else>
								<i style="font-size: 11px;font-weight:bold;">
									<font color="#F7931D">
										To make multiple selections press, 'Ctrl' while choosing options.
									</font>
								</i>
							</g:else>
						</td>
					</tr>
					<tr>
						<th>
							Domain
							<span style="color:red">
								*
							</span>
						</th>
						<th>
							Domain Category
							<span style="color:red">
								*
							</span>
						</th>
						<th>
							Knowledge Dimension
							<span style="color:red">
								*
							</span>
						</th>
						<th>
							Focus
						</th>
					</tr>
					<tr style="font-size: 14px; font-weight: bold;">
						<td>
							<g:select name="domain" id="domain_dialog" from="${lrnDomainlist}" style="width:100%;" value="${pedagogyTech?.domain}" optionKey="id" multiple="true" disabled="${pedagogyTech?true:false}"/>
							<g:if test="${pedagogyTech}">
								<div style="display:none;">
									<g:select name="domain" id="domain_dialog" from="${lrnDomainlist}" style="width:100%;" value="${pedagogyTech?.domain}" optionKey="id" multiple="true"/>
								</div>
							</g:if>
						</td>

						<td>
							<g:select name="category" id="category_dialog" from="${domainList}" style="width:100%;" value="${pedagogyTech?.category}" optionKey="id" multiple="true" disabled="${pedagogyTech?true:false}"/>
							<g:if test="${pedagogyTech}">
								<div style="display:none;">
									<g:select name="category" id="category_dialog" from="${domainList}" style="width:100%;" value="${pedagogyTech?.category}" optionKey="id" multiple="true"/>
								</div>
							</g:if>
						</td>

						<td>
							<g:select name="knowledge" id="knowledge_dialog" from="${KnowledgeDomainlist}" style="width:100%;" value="${pedagogyTech?.knowledge}" optionKey="id" multiple="true" disabled="${pedagogyTech?true:false}" />
							<g:if test="${pedagogyTech}">
								<div style="display:none;">
									<g:select name="knowledge" id="knowledge_dialog" from="${KnowledgeDomainlist}" style="width:100%;" value="${pedagogyTech?.knowledge}" optionKey="id" multiple="true"/>
								</div>
							</g:if>
						</td>

						<td>
							<g:select name="focus" from="${PedagogyActivityFocus.list()}" style="width:114%;" value="${pedagogyTech?.focus}" optionKey="id" optionValue="focus" multiple="true"/>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</tbody>
</table>
<div id="ped_technique">
	<h3 style="height:14px;font-size:13px;">
		Add Activity
	</h3>
	<div style="padding-left:1.2em;">
		<table style="pedding:0;">
			<tr>
				<td colspan="100%">
					<table id="techniqueTableData" class="pedTechTable" border="1">
						<tr>
							<th>
								Title
							</th>
							<th>
								Description
							</th>
							<th>
								Session
							</th>
							<th>
								Example
							</th>
							<th colspan="2">
								Material
							</th>
						</tr>
						<g:if test="${PedagogyActivity.findAllByPedagogyTechnique(pedagogyTech)}">
							<g:each var="activity" in="${PedagogyActivity.findAllByPedagogyTechnique(pedagogyTech)}" status="i">
								<tr>
									<td>
										<g:field type="text" name="pedagogyActivity${i+1}.title" value="${activity?.title}" />
									</td>
									<td>
										<g:field type="text" name="pedagogyActivity${i+1}.description" value="${activity?.description}"/>
									</td>
									<td>
										<g:select name="pedagogyActivity${i+1}.duration" from="${PedagogyActivityDuration.list()}" optionKey="id" optionValue="duration" noSelection="['':'Select one']" value="${activity?.pedagogyActivityDuration?.id}"/>
									</td>
									<td>
										<g:field type="text" name="pedagogyActivity${i+1}.example" value="${activity?.example}" />
									</td>
									<td>
										<g:field type="text" name="pedagogyActivity${i+1}.material" value="${activity?.material}"/>
									</td>
									<td>
										<a href="javascript:" onclick="removeRow(this,${pedagogyTech?true:false});" style="text-decoration:none;font-weight:bold;">
											x
										</a>
									</td>
								</tr>
							</g:each>
						</g:if>
						<g:else>
							<tr>
								<td>
									<g:field type="text" name="pedagogyActivity1.title" value="" />
								</td>
								<td>
									<g:field type="text" name="pedagogyActivity1.description" value=""/>
								</td>
								<td>
									<g:select name="pedagogyActivity1.duration" from="${PedagogyActivityDuration.list()}" optionKey="id" optionValue="duration" noSelection="['':'Select one']"/>
								</td>
								<td>
									<g:field type="text" name="pedagogyActivity1.example" value="" />
								</td>
								<td>
									<g:field type="text" name="pedagogyActivity1.material" value=""/>
								</td>
								<td>
									<a href="javascript:" onclick="removeRowActivity(this,${pedagogyTech?true:false});" style="text-decoration:none;font-weight:bold;">
										x
									</a>
								</td>
							</tr>
						</g:else>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<input style="font-size:13px;" type="button" class="add showHover" title="Add more" value="Add more" onclick="addRowActivity(${pedagogyTech?true:false});">
				</td>
			</tr>
		</table>
	</div>
	<h3 style="height:14px;font-size:13px;">
		Add Reference
	</h3>
	<div style="padding-left:1.2em;">
		<table>
			<tr>
				<td colspan="100%">
					<table id="referenceTableData" class="pedTechTable" border="1">
						<tr>
							<th>
								Title
							</th>
							<th>
								Author
							</th>
							<th>
								Description
							</th>
							<th>
								Link/ISBN
							</th>
							<th colspan="2">
								Type
							</th>
						</tr>
						<g:if test="${PedagogyReference.findAllByPedagogyTechnique(pedagogyTech)}">
							<g:each var="reference" in="${PedagogyReference.findAllByPedagogyTechnique(pedagogyTech)}">
								<tr>
									<td>
										<g:field type="text" name="pedagogyReference1.title" value="${reference.title}" />
									</td>
									<td>
										<g:field type="text" name="pedagogyReference1.author" value="${reference.author}"/>
									</td>
									<td>
										<g:field type="text" name="pedagogyReference1.description" value="${reference.description}"/>
									</td>
									<td>
										<g:field type="text" name="pedagogyReference1.referenceLinkISBN" value="${reference.referenceLinkISBN}" />
									</td>
									<td>
										<g:select name="pedagogyReference1.refeType" from="${imodv6.PedagogyReferenceType.list()}" value="${reference.referenceType?.id}" optionKey="id" optionValue="description" noSelection="['':'Select one']"/>
									</td>
									<td>
										<a href="javascript:" onclick="removeRow(this,${pedagogyTech?true:false});" style="text-decoration:none;font-weight:bold;">
											x
										</a>
									</td>
								</tr>
							</g:each>
						</g:if>
						<g:else>
							<tr>
								<td>
									<g:field type="text" name="pedagogyReference1.title" value="" />
								</td>
								<td>
									<g:field type="text" name="pedagogyReference1.author" value=""/>
								</td>
								<td>
									<g:field type="text" name="pedagogyReference1.description" value=""/>
								</td>
								<td>
									<g:field type="text" name="pedagogyReference1.referenceLinkISBN" value="" />
								</td>
								<td>
									<g:select name="pedagogyReference1.refeType" from="${imodv6.PedagogyReferenceType.list()}" optionKey="id" optionValue="description" noSelection="['':'Select one']"/>
								</td>
								<td>
									<a href="javascript:" onclick="removeRow(this,${pedagogyTech?true:false});" style="text-decoration:none;font-weight:bold;">
										x
									</a>
								</td>
							</tr>
						</g:else>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<input style="font-size:13px;" type="button" class="add showHover" title="Add more" value="Add more" onclick="addRowReference(${pedagogyTech?true:false});">
				</td>
			</tr>
		</table>
	</div>
</div>
