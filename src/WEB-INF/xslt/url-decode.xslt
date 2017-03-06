<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="text" indent="no" encoding="iso-8859-1"/>

  <xsl:param name="url" select="'urn:check%20out%20my%20r%E9sum%E9'"/>

  <xsl:variable name="hex" select="'0123456789ABCDEF'"/>
  <xsl:variable name="ascii"> !"#$%&amp;'()*+,-./0123456789:;&lt;=&gt;?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\]^_`abcdefghijklmnopqrstuvwxyz{|}~</xsl:variable>
  <xsl:variable name="latin1"> ¡¢£¤¥¦§¨©ª«¬­®¯°±²³´µ¶·¸¹º»¼½¾¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞßàáâãäåæçèéêëìíîïðñòóôõö÷øùúûüýþÿ</xsl:variable>

  <xsl:template match="/">
    <xsl:call-template name="decode">
      <xsl:with-param name="encoded" select="$url"/>
    </xsl:call-template>
  </xsl:template>

  <xsl:template name="decode">
    <xsl:param name="encoded"/>
    <xsl:choose>
      <xsl:when test="contains($encoded,'%')">
        <xsl:value-of select="substring-before($encoded,'%')"/>
        <xsl:variable name="hexpair" select="translate(substring(substring-after($encoded,'%'),1,2),'abcdef','ABCDEF')"/>
        <xsl:variable name="decimal" select="(string-length(substring-before($hex,substring($hexpair,1,1))))*16 + string-length(substring-before($hex,substring($hexpair,2,1)))"/>
        <xsl:choose>
          <xsl:when test="$decimal &lt; 127 and $decimal &gt; 31">
            <xsl:value-of select="substring($ascii,$decimal - 31,1)"/>
          </xsl:when>
          <xsl:when test="$decimal &gt; 159">
            <xsl:value-of select="substring($latin1,$decimal - 159,1)"/>
          </xsl:when>
          <xsl:otherwise>?</xsl:otherwise>
        </xsl:choose>
        <xsl:call-template name="decode">
          <xsl:with-param name="encoded" select="substring(substring-after($encoded,'%'),3)"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="$encoded"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

</xsl:stylesheet>