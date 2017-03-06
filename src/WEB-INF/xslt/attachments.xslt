<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:lax="http://informatika.ftn.uns.ac.rs/legal/metalex/1.0/lax"
    xmlns:strict="http://informatika.ftn.uns.ac.rs/legal/metalex/1.0/strict"
    xmlns:fn="http://www.w3.org/2005/xpath-functions"
    xmlns="http://www.w3.org/1999/xhtml">
    
    <xsl:include href="url-encode.xslt"/>
    
    <!-- Should I use RDF or XML for representing attachments? -->
    
    <xsl:template match="/strict:regulation">
        <table>
            <xsl:apply-templates/>
        </table>
    </xsl:template>
    
    <xsl:template match="/lax:regulation">
        <table>
            <xsl:apply-templates/>
        </table>
    </xsl:template>
    
    <xsl:template match="//strict:attachment">
        <tr>
            <td>
                <a target="_blank"><xsl:attribute name="href">Attachment?attachmentUri=<xsl:call-template name="url-encode">
                               <xsl:with-param name="str" select="./@src"/>
                           </xsl:call-template>&amp;fileName=<xsl:call-template name="url-encode">
                            <xsl:with-param name="str" select="."/>
                        </xsl:call-template>
                    </xsl:attribute><xsl:value-of select="." /></a>
            </td>
        </tr>
        <xsl:apply-templates />
    </xsl:template>
    
    <xsl:template match="//lax:attachment">
        <tr>
            <td>
                <a target="_blank"><xsl:attribute name="href">Attachment?attachmentUri=<xsl:call-template name="url-encode">
                               <xsl:with-param name="str" select="./@src"/>
                           </xsl:call-template>&amp;fileName=<xsl:call-template name="url-encode">
                            <xsl:with-param name="str" select="."/>
                        </xsl:call-template>
                    </xsl:attribute><xsl:value-of select="." /></a>
            </td>
        </tr>
        <xsl:apply-templates />
    </xsl:template>
    
    <xsl:template match="//text()">		
       
    </xsl:template>
</xsl:stylesheet>