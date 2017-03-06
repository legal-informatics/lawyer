<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:strict="http://informatika.ftn.uns.ac.rs/legal/metalex/1.0/strict"
    xmlns:lax="http://informatika.ftn.uns.ac.rs/legal/metalex/1.0/lax"
    xmlns="http://www.w3.org/1999/xhtml">
    
    <!-- Use lists instead of headings -->
    
    <xsl:template match="/strict:regulation">
        <ul>
            <xsl:apply-templates/>
        </ul>
    </xsl:template>
    
     <xsl:template match="/lax:regulation">
        <ul>
            <xsl:apply-templates/>
        </ul>
    </xsl:template>
    
    <xsl:template match="//strict:part">		
        <li class="part"><a href=""><xsl:attribute name="href">#<xsl:value-of select="./@id" /></xsl:attribute><xsl:value-of select="./strict:heading" /></a></li>
        <ul>
        	<xsl:apply-templates />
        </ul>
    </xsl:template>
    
    <xsl:template match="//lax:part">		
        <li class="part"><a href=""><xsl:attribute name="href">#<xsl:value-of select="./@id" /></xsl:attribute><xsl:value-of select="./lax:heading" /></a></li>
        <ul>
        	<xsl:apply-templates />
        </ul>
    </xsl:template>
    
    <xsl:template match="//strict:chapter">		
        <li class="chapter"><a href=""><xsl:attribute name="href">#<xsl:value-of select="./@id" /></xsl:attribute><xsl:value-of select="./strict:heading" /></a></li>
        <ul>
        	<xsl:apply-templates />
        </ul>
    </xsl:template>
    
    <xsl:template match="//lax:chapter">		
        <li class="chapter"><a href=""><xsl:attribute name="href">#<xsl:value-of select="./@id" /></xsl:attribute><xsl:value-of select="./lax:heading" /></a></li>
        <ul>
        	<xsl:apply-templates />
        </ul>
    </xsl:template>
    
    <xsl:template match="//strict:section">		
        <li class="section"><a href=""><xsl:attribute name="href">#<xsl:value-of select="./@id" /></xsl:attribute><xsl:value-of select="./strict:heading" /></a></li>
        <ul>
        	<xsl:apply-templates />
        </ul>
    </xsl:template>
    
    <xsl:template match="//lax:section">		
        <li class="section"><a href=""><xsl:attribute name="href">#<xsl:value-of select="./@id" /></xsl:attribute><xsl:value-of select="./lax:heading" /></a></li>
        <ul>
        	<xsl:apply-templates />
        </ul>
    </xsl:template>
    
    <xsl:template match="//strict:subsection">		
        <li class="subsection"><a href=""><xsl:attribute name="href">#<xsl:value-of select="./@id" /></xsl:attribute><xsl:value-of select="./strict:heading" /></a></li>
        <ul>
        	<xsl:apply-templates />
        </ul>
    </xsl:template>
    
    
    <xsl:template match="//lax:subsection">		
        <li class="subsection"><a href=""><xsl:attribute name="href">#<xsl:value-of select="./@id" /></xsl:attribute><xsl:value-of select="./lax:heading" /></a></li>
        <ul>
        	<xsl:apply-templates />
        </ul>
    </xsl:template>
    
    <xsl:template match="//strict:article">		
        <li class="article"><a href=""><xsl:attribute name="href">#<xsl:value-of select="./@id" /></xsl:attribute><xsl:value-of select="./strict:heading" /></a></li>
    </xsl:template>
    
    <xsl:template match="//lax:article">		
        <li class="article"><a href=""><xsl:attribute name="href">#<xsl:value-of select="./@id" /></xsl:attribute><xsl:value-of select="./lax:heading" /></a></li>
    </xsl:template>
    
    <xsl:template match="//text()">		
       
    </xsl:template>
</xsl:stylesheet>